package com.example.tt_xm.data.bean;

import java.util.List;

public class MyCircle {

    /**
     * result : [{"commodityId":2,"content":"emmmmm......","createTime":1551307280000,"greatNum":2,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":568,"image":"http://172.17.8.100/images/small/circle_pic/2019-02-27/3865120190227164120.jpg","nickName":"iQ_fkP9o","userId":8},{"commodityId":2,"content":"aaaaaaaaaaaaaa","createTime":1551306917000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":567,"image":"http://172.17.8.100/images/small/circle_pic/2019-02-27/4713620190227163517.jpg","nickName":"iQ_fkP9o","userId":8},{"commodityId":1,"content":"推荐一波","createTime":1551306817000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":565,"image":"http://172.17.8.100/images/small/circle_pic/2019-02-27/5904820190227163337.jpg","nickName":"iQ_fkP9o","userId":8}]
     * message : 查詢成功
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
         * commodityId : 2
         * content : emmmmm......
         * createTime : 1551307280000
         * greatNum : 2
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * id : 568
         * image : http://172.17.8.100/images/small/circle_pic/2019-02-27/3865120190227164120.jpg
         * nickName : iQ_fkP9o
         * userId : 8
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
