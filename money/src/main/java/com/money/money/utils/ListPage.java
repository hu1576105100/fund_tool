package com.money.money.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author hujun
 * @version 1.0
 * @date 2022/01/18 10:28
 */
@Data
@NoArgsConstructor
public class ListPage<E> {
    /**
     * 当前页面
     */
    private int current = 1;

    /**
     * 显示多少行
     */
    private int size = 10;

    /**
     * 总记录条数
     */
    private int total = 0;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 数据
     */
    private List<E> records;
    /**
     * 数据
     */
    private List<Long> elements;

    private Object object;

    public ListPage(List<E> list, int total,int pageNum, int pageSize){
        this.current=pageNum;
        this.size=pageSize;
        this.total=total;
        this.records=list;
        if (this.total % this.size == 0) {
            this.pages = this.total / this.size;
        } else {
            this.pages = this.total / this.size + 1;
        }
    }
    /**
     * 对list集合进行分页处理
     *
     * @return
     */
    public ListPage(List<E> list, int pageNum, int pageSize) {
        this.current=pageNum;
        this.size=pageSize;
        this.total=list.size();// 记录总数

        this.pages = 0; // 页数

        if(list.size()==0){
            this.records= list;
            return ;
        }
        if (this.total % this.size == 0) {
            this.pages = this.total / this.size;
        } else {
            this.pages = this.total / this.size + 1;
        }
        if(this.pages<this.current){
            this.records=list.subList(this.size*(this.pages-1), (Math.min((this.size * this.pages), this.total)));
        }else{
            this.records=list.subList(this.size*(this.current-1), (Math.min((this.size * this.current), this.total)));
        }
    }
}
