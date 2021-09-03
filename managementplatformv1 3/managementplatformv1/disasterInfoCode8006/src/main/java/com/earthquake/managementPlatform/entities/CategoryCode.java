package com.earthquake.managementPlatform.entities;

import org.springframework.stereotype.Repository;

@Repository
public class CategoryCode {
    private String categoryInfo;


    public String getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public String codeForCategory() {
        switch (categoryInfo) {
            case "人员死亡":
            case "111":
                return "111";
            case "人员受伤":
            case "112":
                return "112";
            case "人员失踪":
            case "113":
                return "113";
            case "房屋破坏土木":
            case "221":
                return "221";
            case "房屋破坏砖木":
            case "222":
                return "222";
            case "房屋破坏砖混":
            case "223":
                return "223";
            case "房屋破坏框架":
            case "224":
                return "224";
            case "房屋破坏其他":
            case "225":
                return "225";
            case "生命线工程灾情交通":
            case "331":
                return "331";
            case "生命线工程灾情供水":
            case "332":
                return "332";
            case "生命线工程灾情输油":
            case "333":
                return "333";
            case "生命线工程灾情燃气":
            case "334":
                return "334";
            case "生命线工程灾情电力":
            case "335":
                return "335";
            case "生命线工程灾情通信":
            case "336":
                return "336";
            case "生命线工程灾情水利":
            case "337":
                return "337";
            case "次生灾害崩塌":
            case "441":
                return "441";
            case "次生灾害滑坡":
            case "442":
                return "442";
            case "次生灾害泥石流":
            case "443":
                return "443";
            case "次生灾害岩溶塌陷":
            case "444":
                return "444";
            case "次生灾害地裂缝":
            case "445":
                return "445";
            case "次生灾害地面沉降":
            case "446":
                return "446";
            case "次生灾害其他":
            case "447":
                return "447";
            case "基本震情":
            case "551":
                return "551";
            case "灾情预测":
            case "552":
                return "552";
        }
        return "000";
    }
}
