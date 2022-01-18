package com.wzg.creat.user.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pm_unified_resources_summery_info_every_time")
public class PmUnifiedResourcesSummeryInfoEveryTime {
    @Id
    private Integer id;

    /**
     * cpu均值
     */
    @Column(name = "cpu_avg")
    private Double cpuAvg;

    /**
     * cpu均峰值
     */
    @Column(name = "cpu_avg_max")
    private Double cpuAvgMax;

    /**
     * 内存均值
     */
    @Column(name = "mem_avg")
    private Double memAvg;

    /**
     * 内存均峰值
     */
    @Column(name = "mem_avg_max")
    private Double memAvgMax;

    /**
     * 资源类型：onlineServer已上线物理机，assignedServer已分配物理机，hypervisor宿主机，vmhost虚拟机
     */
    @Column(name = "resource_type")
    private String resourceType;

    @Column(name = "insert_time")
    private Date insertTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取cpu均值
     *
     * @return cpu_avg - cpu均值
     */
    public Double getCpuAvg() {
        return cpuAvg;
    }

    /**
     * 设置cpu均值
     *
     * @param cpuAvg cpu均值
     */
    public void setCpuAvg(Double cpuAvg) {
        this.cpuAvg = cpuAvg;
    }

    /**
     * 获取cpu均峰值
     *
     * @return cpu_avg_max - cpu均峰值
     */
    public Double getCpuAvgMax() {
        return cpuAvgMax;
    }

    /**
     * 设置cpu均峰值
     *
     * @param cpuAvgMax cpu均峰值
     */
    public void setCpuAvgMax(Double cpuAvgMax) {
        this.cpuAvgMax = cpuAvgMax;
    }

    /**
     * 获取内存均值
     *
     * @return mem_avg - 内存均值
     */
    public Double getMemAvg() {
        return memAvg;
    }

    /**
     * 设置内存均值
     *
     * @param memAvg 内存均值
     */
    public void setMemAvg(Double memAvg) {
        this.memAvg = memAvg;
    }

    /**
     * 获取内存均峰值
     *
     * @return mem_avg_max - 内存均峰值
     */
    public Double getMemAvgMax() {
        return memAvgMax;
    }

    /**
     * 设置内存均峰值
     *
     * @param memAvgMax 内存均峰值
     */
    public void setMemAvgMax(Double memAvgMax) {
        this.memAvgMax = memAvgMax;
    }

    /**
     * 获取资源类型：onlineServer已上线物理机，assignedServer已分配物理机，hypervisor宿主机，vmhost虚拟机
     *
     * @return resource_type - 资源类型：onlineServer已上线物理机，assignedServer已分配物理机，hypervisor宿主机，vmhost虚拟机
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 设置资源类型：onlineServer已上线物理机，assignedServer已分配物理机，hypervisor宿主机，vmhost虚拟机
     *
     * @param resourceType 资源类型：onlineServer已上线物理机，assignedServer已分配物理机，hypervisor宿主机，vmhost虚拟机
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}