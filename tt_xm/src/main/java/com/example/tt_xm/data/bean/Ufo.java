package com.example.tt_xm.data.bean;

import java.util.List;

public class Ufo {

    /**
     * result : [{"commodityId":1,"content":"????","createTime":1552577581000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png","id":786,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-14/0176820190314103301.jpg","nickName":"nanye1112","userId":1636,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552527322000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png","id":785,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-13/4159620190313203522.jpg","nickName":"nanye1112","userId":1636,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552527266000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png","id":784,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-13/1876020190313203426.jpg","nickName":"nanye1112","userId":1636,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552527149000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png","id":783,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-13/3726420190313203229.jpg","nickName":"nanye1112","userId":1636,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552527134000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png","id":782,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-13/1745920190313203214.jpg","nickName":"nanye1112","userId":1636,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552526643000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png","id":781,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-13/4315820190313202403.jpg","nickName":"nanye1112","userId":1636,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552526623000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png","id":780,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-13/1254720190313202343.jpg","nickName":"nanye1112","userId":1636,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552525963000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png","id":779,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-13/3954720190313201243.jpg","nickName":"nanye1112","userId":1636,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552079239000,"greatNum":2,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":778,"image":"","nickName":"do_1plCu","userId":1614,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552079221000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":777,"image":"","nickName":"do_1plCu","userId":1614,"whetherGreat":2}]
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
         * commodityId : 1
         * content : ????
         * createTime : 1552577581000
         * greatNum : 0
         * headPic : http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png
         * id : 786
         * image : http://172.17.8.100/images/small/circle_pic/2019-03-14/0176820190314103301.jpg
         * nickName : nanye1112
         * userId : 1636
         * whetherGreat : 2
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
        private int whetherGreat;

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

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
