package org.fkit.hrm.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
public class Dept implements Serializable{

    private Integer id;
    private String  name;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
