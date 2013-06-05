package com.colosa.qa.automatization.common.extJs;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class ExtJSTree{

	private WebDriver driver;
	private WebElement tree;
	private int timeout;
	private WebElement currentNode;
	private WebElement root = null;

    /**
     * Class used to manage - navigate ext-js tree
     * @param tree the web element that is most near to the element that has the class atribute= " x-tree-root-ct"
     *             The first element with this class is selected as the root of the tree.
     * @param driver the web driver
     */
	public ExtJSTree(WebElement tree, WebDriver driver) throws Exception {
		this.driver = driver;
		this.tree = tree;
		//this.timeout = timeout;
		//element with class x-tree-root-ct
        this.root = tree.findElement(By.className("x-tree-root-ct"));//(By.xpath("div/div/ul/div"));
        //check if there's another level
        WebElement auxRoot = null;
        auxRoot = this.root.findElement(By.className("x-tree-root-node"));
        if(auxRoot != null){
            //change of root node
            this.root =auxRoot;
        }

		this.currentNode = this.root;

        //check if node was found
        if(this.root == null){
            throw new Exception("No ExtJs tree structure found.");
        }
	}

    /**
     * Go to element that represent the root "/"
     */
    public void gotoRoot(){
		//this.root.findElement(By.xpath("div/a/span")).click();
        this.root = tree.findElement(By.className("x-tree-root-ct"));
		this.currentNode = this.root;
	}

	public void refresh(){
		this.tree.findElement(By.xpath("div[1]/div[1]")).click();
		//talvez esperar a que se termine de cargar
	}

    public List<ExtJSTreeNode> getListRootNodes(){
        List<WebElement> rootNodes = null;

        //search root nodes
        rootNodes = this.root.findElements(By.cssSelector(this.root.getTagName() + " > li.x-tree-node"));  //style="" [style='']

        List<ExtJSTreeNode> listTreeRootNodes = new ArrayList<ExtJSTreeNode>(rootNodes.size());

        for (WebElement rootNode : rootNodes) {
            listTreeRootNodes.add(new ExtJSTreeNode(rootNode, this.driver));
        }

        return listTreeRootNodes;
    }

    public ExtJSTreeNode gotoNode(String path) throws Exception {
        return gotoNode(path, false);
    }

    /**
     * Select the specified node in the tree from the root
     * @param path The path to the node, start with /rootNode/nodelevel1/nodelevel2 etc.
     *             The complete path must be specified. Regular expressions are supported.
     * @return The found tree node
     */
	public ExtJSTreeNode gotoNode(String path, Boolean useRegularExpresion) throws Exception {
		String itemToSearch = null;
		List<WebElement> rootNodes = null;
        List<WebElement> filterNodes = null;
        ExtJSTreeNode rootNode = null;
        ExtJSTreeNode auxRootNode = null;
        ExtJSTreeNode resultWebElement = null;
        String searchPath = path;

        //search root nodes
        rootNodes = this.root.findElements(By.cssSelector(this.root.getTagName() + " > li.x-tree-node"));  //style="" [style='']

        //search first node from left of path (root node)
        String nodeName = getLeftNodePath(searchPath);
        searchPath = removeLeftNodePath(searchPath);

        //list of nodes
        System.out.println("ExtJsTree->Number of root nodes found:" + rootNodes.size());
        System.out.println("ExtJsTree->Search root node:" + nodeName);

        //verify if list of nodes is available
        if(rootNodes == null){
            throw new Exception("ExtJsTree->No root nodes detected in tree structure.");
        }

        //x-tree-root-ct div.x-tree-root-node
        //search the root node in the path
        for(WebElement rootNodeElement:rootNodes){
            //check treenode text
            auxRootNode = new ExtJSTreeNode(rootNodeElement, this.driver);
            //
            System.out.println("ExtJsTree->search rootNode:" + auxRootNode.getNodeText());

            if(useRegularExpresion){
                if(auxRootNode.getNodeText().matches(nodeName)){
                    rootNode = auxRootNode;
                    break;
                }
            }else{
                if(auxRootNode.getNodeText().equals(nodeName)){
                    rootNode = auxRootNode;
                    break;
                }
            }
        }

        //verify if root node is available
        if(rootNode == null){
            throw new Exception("ExtJsTree->Root node not found.");
        }

        //search for child nodes?
        if(searchPath.trim().equals("")){
            //this is the search node
            return rootNode;
        }

        System.out.println("ExtJsTree-> Root node found:" + rootNode.getNodeText());

        resultWebElement = gotoNodeFromNode(rootNode, searchPath, useRegularExpresion);

        return resultWebElement;

        /*
		WebElement elm = (path.charAt(0)=='/')?this.root:this.currentNode;

		path = (path.charAt(path.length()-1)=='/')?path.substring(0, path.length()-1):path;
		path = (path.charAt(0)=='/')?path.substring(1):path;
		if(path.indexOf('/')>0)
		{
			itemToSearch = path.substring(0, path.indexOf('/'));
			path = path.substring(itemToSearch.length()+1);
		}
		else
			itemToSearch = path;

		childs = elm.findElements(By.xpath("ul/li"));

		for(WebElement child:childs){
			if(child.findElement(By.xpath("div/a/span")).getText().equals(itemToSearch)){
				elm = child;
				this.currentNode = elm;
				break;
			}
		}

		elm.findElement(By.xpath("div/a/span")).click();

		if(path.equals(itemToSearch))
			return elm;
		else
			return gotoNode(path);
			*/
	}

    public ExtJSTreeNode gotoNodeFromNode(ExtJSTreeNode currentNode, String path) throws Exception {
        return this.gotoNodeFromNode(currentNode, path, false);
    }


        /**
         * Find the node from the specified node, the path must not include the current node
         * If the node is not found null is returned.
         * @param currentNode the current node, is an element with the class = "x-tree-node"
         * @param path path to the node, the current node must not be included.
         * @return
         */
    public ExtJSTreeNode gotoNodeFromNode(ExtJSTreeNode currentNode, String path, Boolean useRegularExpresion) throws Exception {
        ExtJSTreeNode rootNode = currentNode;
        //ExtJSTreeNode auxRootNode = null;
        String searchPath = path;
        Boolean nodeFound =false;

        //current path
        System.out.println("ExtJsTree->GotoNodeFromNode->current node Path:" + path);

        while(!searchPath.trim().equals("")){
            //verify if node is found in level
            nodeFound =false;

            //search in list of nodes
            //search first node from left of path (root node)
            String auxNodeName = getLeftNodePath(searchPath);
            searchPath = removeLeftNodePath(searchPath);
            System.out.println("ExtJsTree->GotoNodeFromNode->find node:" + auxNodeName);

            //count child nodes
            System.out.println("ExtJsTree->GotoNodeFromNode->child nodes:" + rootNode.getListChildNodes().size());
            for (ExtJSTreeNode childNode : rootNode.getListChildNodes()) {

                //check treenode text
                //auxRootNode = new ExtJSTreeNode(webElement);
                System.out.println("ExtJsTree->GotoNodeFromNode->search childNode:" + childNode.getNodeText());
                if(useRegularExpresion){
                    if(childNode.getNodeText().matches(auxNodeName)){
                        rootNode = childNode;
                        nodeFound = true;
                        break;
                    }
                }else{
                    if(childNode.getNodeText().equals(auxNodeName)){
                        rootNode = childNode;
                        nodeFound = true;
                        break;
                    }
                }
            };
            //continue with the next level in path
            if(nodeFound){
                //check if we are at the end of the path
                if(!searchPath.trim().equals("")){
                    //continue searching the path
                    //assign the new root node
                    //the found node was assigned to rootNode
                } else
                {
                    //the complete path was found
                    break;
                }
            }else{
                //node not found, return error
                break;
            }
        }
        //the found node is rootNode
        if(nodeFound){
            //node found
            System.out.println("Node found:" + rootNode.getNodeText());
            return rootNode;
        }
        else{
            throw new Exception("ExtJsTree->Node not found.");
        }
    }

	public WebElement getRootNode(){
		return this.root;
	}

	public WebElement getCurrentNode(){
		return this.currentNode;
	}

    public String getLeftNodePath(String path){
        String workingPath = path;

        workingPath = removeSeparatorPath(workingPath);

        if(path.trim().equals("")){
            return "";
        }

        String[] splits = workingPath.split("/");

        //se trata de
        return splits[0];
    }

    public String removeLeftNodePath(String path){
        String workingPath = path;

        workingPath = removeSeparatorPath(workingPath);

        if(workingPath.trim().equals("")){
            return "";
        }

        int firstIndex = workingPath.indexOf("/");

        //there's no more nodes
        if(firstIndex == -1){
            return "";
        }
        //se trata de
        return workingPath.substring(firstIndex);
    }

    public String removeSeparatorPath(String path){
        String workingPath = path;

        if(path.trim().equals("")){
            return "";
        }

        //quitar el nodo root si existe
        if(workingPath.charAt(0) == '/'){
            workingPath = workingPath.substring(1);
        }
        //remove the last character
        if(workingPath.charAt(workingPath.length()-1) == '/'){
            workingPath = workingPath.substring(0, workingPath.length()-1);
        }
        //se trata de
        return workingPath;
    }
}
