package com.hengda.frame.commonadapter.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：祝文飞（Tailyou）
 * 邮箱：tailyou@163.com
 * 时间：2017/2/3 9:57
 * 描述：
 */
public class DataUtil {

    public static String[] nums = {"一", "二", "三", "四"};

    public static List<DataItem> getDataItems() {
        List<DataItem> dataItems = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            dataItems.add(new DataItem("item" + i, "第" + nums[i / 4] + "单元", i));
        }
        return dataItems;
    }

    public static class DataItem {
        public String name;
        public String unitName;
        public int index;

        public DataItem(String name, String unitName, int index) {
            this.name = name;
            this.unitName = unitName;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public String getUnitName() {
            return unitName;
        }

        public int getIndex() {
            return index;
        }
    }

}
