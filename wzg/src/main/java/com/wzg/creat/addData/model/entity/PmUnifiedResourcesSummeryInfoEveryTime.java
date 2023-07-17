package com.wzg.creat.addData.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzg
 * @since 2022-01-19
 */
@TableName("pm_unified_resources_summery_info_every_time")
@ApiModel(value = "PmUnifiedResourcesSummeryInfoEveryTime对象", description = "")
public class PmUnifiedResourcesSummeryInfoEveryTime implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("cpu均值")
    private Double cpuAvg;

    @ApiModelProperty("cpu均峰值")
    private Double cpuAvgMax;

    @ApiModelProperty("内存均值")
    private Double memAvg;

    @ApiModelProperty("内存均峰值")
    private Double memAvgMax;

    @ApiModelProperty("资源类型：onlineServer已上线物理机，assignedServer已分配物理机，hypervisor宿主机，vmhost虚拟机")
    private String resourceType;

    private LocalDateTime insertTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCpuAvg() {
        return cpuAvg;
    }

    public void setCpuAvg(Double cpuAvg) {
        this.cpuAvg = cpuAvg;
    }

    public Double getCpuAvgMax() {
        return cpuAvgMax;
    }

    public void setCpuAvgMax(Double cpuAvgMax) {
        this.cpuAvgMax = cpuAvgMax;
    }

    public Double getMemAvg() {
        return memAvg;
    }

    public void setMemAvg(Double memAvg) {
        this.memAvg = memAvg;
    }

    public Double getMemAvgMax() {
        return memAvgMax;
    }

    public void setMemAvgMax(Double memAvgMax) {
        this.memAvgMax = memAvgMax;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public LocalDateTime getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(LocalDateTime insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        return "PmUnifiedResourcesSummeryInfoEveryTime{" +
        "id=" + id +
        ", cpuAvg=" + cpuAvg +
        ", cpuAvgMax=" + cpuAvgMax +
        ", memAvg=" + memAvg +
        ", memAvgMax=" + memAvgMax +
        ", resourceType=" + resourceType +
        ", insertTime=" + insertTime +
        "}";
    }
}
