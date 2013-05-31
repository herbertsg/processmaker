package com.colosa.qa.automatization.common.extJs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 5/7/13
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExtJSTreeNode {
    WebElement treeNode = null;

    /**
     * Initialized with the webelement that represent a Tree-node <li class="x-tree-node"></li>
     * @param treeNode
     */
    public ExtJSTreeNode(WebElement treeNode){
        this.treeNode = treeNode;
    }

    public String getNodeText(){
        WebElement node = this.treeNode.findElement(By.cssSelector("div.x-tree-node-el a.x-tree-node-anchor span"));
        return node.getText();
    }

    public int countChildNodes(){
        List<WebElement> childList = this.treeNode.findElements(By.cssSelector("ul.x-tree-node-ct li.x-tree-node"));
        return childList.size();
    }

    public List<ExtJSTreeNode> getListChildNodes(){
        List<WebElement> childList = this.treeNode.findElements(By.cssSelector(this.treeNode.getTagName() + " > ul.x-tree-node-ct li.x-tree-node:not([style='display: none;'])")); //style="display: none;"

        List<ExtJSTreeNode> listTreeChildNodes = new ArrayList<ExtJSTreeNode>(childList.size());

        for (WebElement childNode : childList) {
            listTreeChildNodes.add(new ExtJSTreeNode(childNode));
        }
        return listTreeChildNodes;
    }

    public WebElement getWebElementNode(){
        return this.treeNode;
    }

}
