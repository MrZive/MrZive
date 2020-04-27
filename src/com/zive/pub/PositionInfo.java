package com.zive.pub;
/**
 * 职位信息
 * @author Lsenrong
 * @date Nov 3, 2017 10:21:03 AM
 * @Description: TODO(描述)
 */
public class PositionInfo {
	/**
	 * 唯一id
	 */
    private String id;
    /**
     * 职位编号
     */
    private String no;
    /**
     * 职位名称
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 机构id
     */
    private String structureId;
    /**
     * 机构名称
     */
    private String structureName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStructureId() {
		return structureId;
	}
	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}
	public String getStructureName() {
		return structureName;
	}
	public void setStructureName(String structureName) {
		this.structureName = structureName;
	}
    
}
