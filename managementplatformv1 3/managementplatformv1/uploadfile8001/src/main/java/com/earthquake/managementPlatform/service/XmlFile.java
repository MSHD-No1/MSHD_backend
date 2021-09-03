package com.earthquake.managementPlatform.service;


public class XmlFile extends DisasterFile{
    public XmlFile() {
        this.fileBehavior = new XmlBehavior();
    }

    public XmlFile(String filePath){
        this.fileBehavior = new XmlBehavior();
        this.filePath = filePath;
    }
}
