package com.example.tt_xm.data.bean;

import java.util.List;

public class Address {

    /**
     * result : [{"address":"北京 海淀区 八维","createTime":1551144611000,"id":883,"phone":"15811485711","realName":"周小黑","userId":8,"whetherDefault":2,"zipCode":"101010"},{"address":"上海 海淀区 ","createTime":1551144748000,"id":885,"phone":"15811485714","realName":"张老狗","userId":8,"whetherDefault":2,"zipCode":"101010"},{"address":"山东 ","createTime":1551144781000,"id":886,"phone":"13112345678","realName":"周扒皮","userId":8,"whetherDefault":2,"zipCode":"101010"},{"address":"北京市   北京市   昌平区44","createTime":1551473080000,"id":994,"phone":"15811485711","realName":"1","userId":8,"whetherDefault":2,"zipCode":"101010"},{"address":"安徽省   安庆市   枞阳县4543","createTime":1551473361000,"id":995,"phone":"15811111111","realName":"2","userId":8,"whetherDefault":2,"zipCode":"101010"},{"address":"安徽省   安庆市   枞阳县1452","createTime":1551808181000,"id":1122,"phone":"15811485711","realName":"11","userId":8,"whetherDefault":2,"zipCode":"101010"}]
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

    public static class ResultBean {
        /**
         * address : 北京 海淀区 八维
         * createTime : 1551144611000
         * id : 883
         * phone : 15811485711
         * realName : 周小黑
         * userId : 8
         * whetherDefault : 2
         * zipCode : 101010
         */

        private String address;
        private long createTime;
        private int id;
        private String phone;
        private String realName;
        private int userId;
        private int whetherDefault;
        private String zipCode;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherDefault() {
            return whetherDefault;
        }

        public void setWhetherDefault(int whetherDefault) {
            this.whetherDefault = whetherDefault;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
