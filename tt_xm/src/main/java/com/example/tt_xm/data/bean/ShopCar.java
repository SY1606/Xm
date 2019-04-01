package com.example.tt_xm.data.bean;

import java.io.Serializable;
import java.util.List;

public class ShopCar {

    /**
     * result : [{"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","count":3,"pic":"http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg","price":78}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * commodityId : 19
         * commodityName : 环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋
         * count : 3
         * pic : http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg
         * price : 78
         */

        private int commodityId;
        private String commodityName;
        private int count;
        private String pic;
        private int price;
        private boolean FatherChecked;
        private int defoultNumber = 1;

        public ResultBean(int defoultNumber) {
            this.defoultNumber = defoultNumber;
        }

        public int getDefoultNumber() {
            return defoultNumber;
        }

        public void setDefoultNumber(int defoultNumber) {
            this.defoultNumber = defoultNumber;
        }
        public ResultBean(boolean fatherChecked, boolean childChdecked) {
            FatherChecked = fatherChecked;

        }

        public boolean getFatherChecked() {
            return FatherChecked;
        }

        public void setFatherChecked(boolean fatherChecked) {
            FatherChecked = fatherChecked;
        }

        public ResultBean(int commodityId, String commodityName, String pic, int price, int count, boolean FatherChecked,int defoultNumber) {
            this.commodityId = commodityId;
            this.commodityName = commodityName;
            this.pic = pic;
            this.price = price;
            this.count = count;
            this.FatherChecked = FatherChecked;
            this.defoultNumber =defoultNumber;
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
