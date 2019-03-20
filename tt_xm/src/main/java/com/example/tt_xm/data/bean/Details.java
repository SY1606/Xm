package com.example.tt_xm.data.bean;

public class Details {

    /**
     * result : {"categoryId":"1001004003","categoryName":"豆豆鞋","commentNum":14,"commodityId":27,"commodityName":"休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋","describe":"棕色,35码","details":"<div class=\"dc-img\">\r\n    <div class=\"dc-img-detail\">\r\n                        <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/81/afd8d460-57ef-45e1-b468-7cd40c890e29.jpg\" class=\"J-mer-bigImg\" data-original=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/81/afd8d460-57ef-45e1-b468-7cd40c890e29.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/47/c8bd59a5-1b24-47c6-b657-339fc7b3140b.jpg\" class=\"J-mer-bigImg\" data-original=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/47/c8bd59a5-1b24-47c6-b657-339fc7b3140b.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/41/e296f082-da68-42da-bb1f-1777ad5aa810.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/37/e635bd64-448a-46da-867c-6a185e4ab89f.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/57/fa9965b7-14a9-4bae-b57a-afbf1d1d0a73.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/78/1a1cd974-65b4-4432-9a26-e50bf80dadbe.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/28/37de2af8-5c90-47f6-a690-c9f69e26b67b.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/53/8f03e12f-2494-46df-aeb5-a60f90886223.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/37/05717b6e-0ed7-418d-8e90-fd5475634d17.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/195/49cce1ac-c94b-4ba9-b087-1628012cef01.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/113/0d347162-10c9-4804-903d-57bf904ee6fe.jpg\">\r\n        <\/div>\r\n            <\/div>\r\n    <div class=\"dc-img-con\">\r\n            <\/div>\r\n    <div class=\"dc-txt-con\">\r\n            <\/div>\r\n<\/div>","picture":"http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/5.jpg","price":139,"saleNum":0,"stock":9999,"weight":1}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * categoryId : 1001004003
         * categoryName : 豆豆鞋
         * commentNum : 14
         * commodityId : 27
         * commodityName : 休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋
         * describe : 棕色,35码
         * details : <div class="dc-img">
         <div class="dc-img-detail">
         <div class="img-6xx-bg">
         <img src="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/81/afd8d460-57ef-45e1-b468-7cd40c890e29.jpg" class="J-mer-bigImg" data-original="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/81/afd8d460-57ef-45e1-b468-7cd40c890e29.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/47/c8bd59a5-1b24-47c6-b657-339fc7b3140b.jpg" class="J-mer-bigImg" data-original="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/47/c8bd59a5-1b24-47c6-b657-339fc7b3140b.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http:http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/41/e296f082-da68-42da-bb1f-1777ad5aa810.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http:http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/37/e635bd64-448a-46da-867c-6a185e4ab89f.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http:http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/57/fa9965b7-14a9-4bae-b57a-afbf1d1d0a73.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http:http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/78/1a1cd974-65b4-4432-9a26-e50bf80dadbe.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http:http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/28/37de2af8-5c90-47f6-a690-c9f69e26b67b.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http:http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/53/8f03e12f-2494-46df-aeb5-a60f90886223.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http:http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/37/05717b6e-0ed7-418d-8e90-fd5475634d17.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http:http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/195/49cce1ac-c94b-4ba9-b087-1628012cef01.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http:http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg3.com/upload/merchandise/pdcvis/2018/11/09/113/0d347162-10c9-4804-903d-57bf904ee6fe.jpg">
         </div>
         </div>
         <div class="dc-img-con">
         </div>
         <div class="dc-txt-con">
         </div>
         </div>
         * picture : http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/5.jpg
         * price : 139
         * saleNum : 0
         * stock : 9999
         * weight : 1
         */

        private String categoryId;
        private String categoryName;
        private int commentNum;
        private int commodityId;
        private String commodityName;
        private String describe;
        private String details;
        private String picture;
        private int price;
        private int saleNum;
        private int stock;
        private int weight;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
