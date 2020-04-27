package com.zive.pub;

import java.util.Date;
/**
 * 人员
 * @author lwf
 *
 */
public class Employee {
    private String id;
    private String employeeNo;
    private String name;
    private String nickName;
    private String gender;
    private Date birthday;
    private String birthdayCalendarType;
    private String married;
    private Integer nation;
    private String politicsStatus;
    private String education;
    private String hobby;
    private String phone;
    private String email;
    private String headImg;
    private String major;
    private String graduationSchool;
    private Date graduationDate;
    private int nativePlace;
    private String identityCard;
    private String identityAddress;
    private String residentialAddress;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private Date jobDatePre;
    private Date jobDateReg;
    private Date entryDate;
    private Date healthCardBeginDate;
    private Date healthCardEndDate;
    private Boolean haveDrivingLicense;
    private Boolean haveWorkCard;
    private Boolean submitIdentityCard;
    private Boolean submitEducationCard;
    private Boolean submitHealthCard;
    private Integer status;
    private String remark;
    private Date createDate;
    private String createUserId;
    
//    部门id
    private String structureId;
    
//    职位id
    private Integer positionId;
    
    
//   宗教信仰 
    private String religion;
    
//  邮政编码  
    private String postcode;
    
//    户口所在地
    private String houkouAddr;
    
//    技术职称
    private String skill;
    
//    档案调入时间
    private Date fileInputDate;
    
//    档案号
    private String fileNum;
    
//    手机号码
    private String cellPhone;
    
//    直线号码
    private String mainPhone;
    
//    分机号码
    private String minPhone;
    
//    是否加入通讯录
    private String addList;
    
//    职位名称
    private String positionName;
    
//    部门名称
    private String structureName;
    
//    离职时间
    private Date leavedTime;
    
//  合同开始时间
    private Date contractStartDate;
    
//    合同结束时间
    private Date contractEndDate;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getBirthdayCalendarType() {
		return birthdayCalendarType;
	}
	public void setBirthdayCalendarType(String birthdayCalendarType) {
		this.birthdayCalendarType = birthdayCalendarType;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public Integer getNation() {
		return nation;
	}
	public void setNation(Integer nation) {
		this.nation = nation;
	}
	public String getPoliticsStatus() {
		return politicsStatus;
	}
	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGraduationSchool() {
		return graduationSchool;
	}
	public void setGraduationSchool(String school) {
		this.graduationSchool = school;
	}
	public Date getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}
	public int getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(int nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getIdentityAddress() {
		return identityAddress;
	}
	public void setIdentityAddress(String identityAddress) {
		this.identityAddress = identityAddress;
	}
	public String getResidentialAddress() {
		return residentialAddress;
	}
	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}
	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Date getJobDatePre() {
		return jobDatePre;
	}
	public void setJobDatePre(Date jobDatePre) {
		this.jobDatePre = jobDatePre;
	}
	public Date getHealthCardBeginDate() {
		return healthCardBeginDate;
	}
	public void setHealthCardBeginDate(Date healthCardBeginDate) {
		this.healthCardBeginDate = healthCardBeginDate;
	}
	public Date getJobDateReg() {
		return jobDateReg;
	}
	public void setJobDateReg(Date jobDateReg) {
		this.jobDateReg = jobDateReg;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getHealthCardEndDate() {
		return healthCardEndDate;
	}
	public void setHealthCardEndDate(Date healthCardEndDate) {
		this.healthCardEndDate = healthCardEndDate;
	}
	public Boolean getHaveDrivingLicense() {
		return haveDrivingLicense;
	}
	public void setHaveDrivingLicense(Boolean haveDrivingLicense) {
		this.haveDrivingLicense = haveDrivingLicense;
	}
	public Boolean getHaveWorkCard() {
		return haveWorkCard;
	}
	public void setHaveWorkCard(Boolean haveWorkCard) {
		this.haveWorkCard = haveWorkCard;
	}
	public Boolean getSubmitIdentityCard() {
		return submitIdentityCard;
	}
	public void setSubmitIdentityCard(Boolean submitIdentityCard) {
		this.submitIdentityCard = submitIdentityCard;
	}
	public Boolean getSubmitEducationCard() {
		return submitEducationCard;
	}
	public void setSubmitEducationCard(Boolean submitEducationCard) {
		this.submitEducationCard = submitEducationCard;
	}
	public Boolean getSubmitHealthCard() {
		return submitHealthCard;
	}
	public void setSubmitHealthCard(Boolean submitHealthCard) {
		this.submitHealthCard = submitHealthCard;
	}
	public String getStructureId() {
		return structureId;
	}
	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getHoukouAddr() {
		return houkouAddr;
	}
	public void setHoukouAddr(String houkouAddr) {
		this.houkouAddr = houkouAddr;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public Date getFileInputDate() {
		return fileInputDate;
	}
	public void setFileInputDate(Date fileInputDate) {
		this.fileInputDate = fileInputDate;
	}
	public String getFileNum() {
		return fileNum;
	}
	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getMainPhone() {
		return mainPhone;
	}
	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}
	public String getMinPhone() {
		return minPhone;
	}
	public void setMinPhone(String minPhone) {
		this.minPhone = minPhone;
	}
	public String getAddList() {
		return addList;
	}
	public void setAddList(String addList) {
		this.addList = addList;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getStructureName() {
		return structureName;
	}
	public void setStructureName(String structureName) {
		this.structureName = structureName;
	}
	public Date getLeavedTime() {
		return leavedTime;
	}
	public void setLeavedTime(Date leavedTime) {
		this.leavedTime = leavedTime;
	}
	public Date getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public Date getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	@Override
	public String toString() {
		return "EmployeeInfoModel [id=" + id + ", employeeNo=" + employeeNo + ", name=" + name + ", nickName=" + nickName + ", gender=" + gender + ", birthday=" + birthday + ", birthdayCalendarType="
				+ birthdayCalendarType + ", married=" + married + ", nation=" + nation + ", politicsStatus=" + politicsStatus + ", education=" + education + ", hobby=" + hobby + ", phone=" + phone
				+ ", email=" + email + ", headImg=" + headImg + ", major=" + major + ", graduationSchool=" + graduationSchool + ", graduationDate=" + graduationDate + ", nativePlace=" + nativePlace
				+ ", identityCard=" + identityCard + ", identityAddress=" + identityAddress + ", residentialAddress=" + residentialAddress + ", emergencyContactName=" + emergencyContactName
				+ ", emergencyContactPhone=" + emergencyContactPhone + ", jobDatePre=" + jobDatePre + ", jobDateReg=" + jobDateReg + ", entryDate=" + entryDate + ", healthCardBeginDate="
				+ healthCardBeginDate + ", healthCardEndDate=" + healthCardEndDate + ", haveDrivingLicense=" + haveDrivingLicense + ", haveWorkCard=" + haveWorkCard + ", submitIdentityCard="
				+ submitIdentityCard + ", submitEducationCard=" + submitEducationCard + ", submitHealthCard=" + submitHealthCard + ", status=" + status + ", remark=" + remark + ", createDate="
				+ createDate + ", createUserId=" + createUserId + ", structureId=" + structureId + ", positionId=" + positionId + ", religion=" + religion + ", postcode=" + postcode + ", houkouAddr="
				+ houkouAddr + ", skill=" + skill + ", fileInputDate=" + fileInputDate + ", fileNum=" + fileNum + ", cellPhone=" + cellPhone + ", mainPhone=" + mainPhone + ", minPhone=" + minPhone
				+ ", addList=" + addList + ", positionName=" + positionName + ", structureName=" + structureName + ", leavedTime=" + leavedTime + ", contractStartDate=" + contractStartDate
				+ ", contractEndDate=" + contractEndDate + "]";
	}
    
}
