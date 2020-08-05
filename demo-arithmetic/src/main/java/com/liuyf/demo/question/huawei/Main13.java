package com.liuyf.demo.question.huawei;

import lombok.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Directory删除
 * <p>
 * 某文件系统中有N个目录，每个目录都一个独一无二的ID。每个目录只有一个父目录，但每个父目录下可以有零个或者多个子目录，目录结构呈树状结构。
 * <p>
 * 假设，根目录的ID为0，且根目录没有父目录，其他所有目录的ID用唯一的正整数表示，并统一编号。
 * <p>
 * 现给定目录ID和其父目录ID的对应父子关系表[子目录ID，父目录ID]，以及一个待删除的目录ID，请计算并返回一个ID序列，表示因为删除指定目录后剩下的所有目录，返回的ID序列以递增序输出。
 * <p>
 * 注意：
 * <p>
 * 1、被删除的目录或文件编号一定在输入的ID序列中；
 * <p>
 * 2、当一个目录删除时，它所有的子目录都会被删除。
 * <p>
 * 输入描述:
 * <p>
 * 输入的第一行为父子关系表的长度m；接下来的m行为m个父子关系对；最后一行为待删除的ID。序列中的元素以空格分割，参见样例。
 * <p>
 * 输出描述:
 * <p>
 * 输出一个序列，表示因为删除指定目录后，剩余的目录ID。
 * <p>
 * 示例1
 * <p>
 * 输入
 * <p>
 * 5
 * <p>
 * 8 6
 * <p>
 * 10 8
 * <p>
 * 6 0
 * <p>
 * 20 8
 * <p>
 * 2 6
 * <p>
 * 8
 * <p>
 * 输出
 * <p>
 * 2 6
 * <p>
 * <p>
 * Created by liuyf on 2020/8/5.
 */

public class Main13 {


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int lineNumber = Integer.parseInt(bf.readLine());

        Map<Integer, TreeSet<Integer>> parent_child_rel = new TreeMap<>();


        for (int i = 0; i < lineNumber; i++) {
            String inputLine = bf.readLine();
            String[] inputNumbers = inputLine.split(" ");
            Integer pid = Integer.valueOf(inputNumbers[1]), id = Integer.valueOf(inputNumbers[0]);
            if (!parent_child_rel.containsKey(pid)) {
                parent_child_rel.put(pid, new TreeSet<>());
            }
            parent_child_rel.get(pid).add(id);
        }
        Integer toRemove = Integer.valueOf(bf.readLine());
        Dir rootDir = new Dir(0);
        buildTree(rootDir, parent_child_rel);
        rootDir.print();
        rootDir.removeChild(toRemove);
        rootDir.print();

    }

    @Data
    public static class Dir  {
        Integer id ;
        TreeMap<Integer,Dir> subDirs = new TreeMap<>();

        public Dir(Integer id) {
            this.id = id;
        }

        public void asChild(Dir dir) {
            subDirs.put(dir.getId(),dir);
        }

        public void removeChild(Integer id) {
            if (subDirs.containsKey(id)) {
                subDirs.remove(id);
            } else {
                subDirs.values().forEach( eachSubDir->{
                    eachSubDir.removeChild(id);
                });
            }
        }

        public void print(){
            System.out.print(id+" ");
            if(!subDirs.isEmpty()){
                subDirs.values().forEach(Dir::print);

            }
        }
    }

    public static void buildTree(Dir p ,Map<Integer, TreeSet<Integer>> parent_child_rel) {
        TreeSet<Integer> childIds = parent_child_rel.remove(p.getId());
        if (childIds == null || childIds.isEmpty()) {
            return;
        }

        childIds.forEach( c->{
            Dir child = new Dir(c);
            p.asChild(child);
            buildTree(child, parent_child_rel);
        });
    }

}
