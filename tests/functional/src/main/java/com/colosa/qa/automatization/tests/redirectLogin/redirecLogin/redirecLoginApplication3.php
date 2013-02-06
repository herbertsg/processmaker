<?php
try {
  $oHeadPublisher = &headPublisher::getSingleton();
  
  $oHeadPublisher->addContent("redirecLogin/redirecLoginApplication3"); //Adding a html file .html.
  $oHeadPublisher->addExtJsScript("redirecLogin/redirecLoginApplication3", false); //Adding a javascript file .js

  G::RenderPage("publish", "extJs");
} catch (Exception $e) {
  $G_PUBLISH = new Publisher;
  
  $aMessage["MESSAGE"] = $e->getMessage();
  $G_PUBLISH->AddContent("xmlform", "xmlform", "redirecLogin/messageShow", "", $aMessage);
  G::RenderPage("publish", "blank");
}
?>