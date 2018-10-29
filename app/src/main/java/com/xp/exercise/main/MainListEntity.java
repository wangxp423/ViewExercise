package com.xp.exercise.main;

import java.util.ArrayList;

/**
 * @类描述：首页 一级菜单
 * @创建人：Wangxiaopan
 * @创建时间：2018/10/25 0025 16:17
 * @修改人：
 * @修改时间：2018/10/25 0025 16:17
 * @修改备注：
 */
public class MainListEntity {
    private ArrayList<LevelOneEntity> datas;

    public ArrayList<LevelOneEntity> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<LevelOneEntity> data) {
        this.datas = data;
    }

    public static class LevelOneEntity {
        private int id;
        private String title;
        private String tag;
        private ArrayList<LevelTwoEntity> childs;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTitle() {

            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<LevelTwoEntity> getChilds() {
            return childs;
        }

        public void setChilds(ArrayList<LevelTwoEntity> childs) {
            this.childs = childs;
        }
    }

    public static class LevelTwoEntity {
        private int id;
        private String tag;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
