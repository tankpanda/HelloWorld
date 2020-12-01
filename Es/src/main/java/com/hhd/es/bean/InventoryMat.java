package com.hhd.es.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
*   进销存物料表:实体类
 *
*  @author jinzhaopo
*  @since 2020-10-10 18:12:20
*/
@Document(indexName = "inventory_mat_with_repository")
public class InventoryMat implements Serializable {

    /**
     * 物料ID
     */
    @Id
    private Long id;

    /**
     * 物料名称
     */
    @Field(type = FieldType.Keyword)
    private String matName;

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 产品名称
     */
    @Field(type = FieldType.Keyword)
    private String productName;

    /**
     * 厂商编码
     */
    @Field(type = FieldType.Keyword)
    private String producerCode;

    /**
     * 产品规格ID
     */
    private Long productSpecId;

    /**
     * 规格名称
     */
    @Field(type = FieldType.Keyword)
    private String productSpecName;

    /**
     * 子规格名称
     */
    @Field(type = FieldType.Keyword)
    private String productSpecNameExt;

    /**
     * 项目Id
     */
    private Long categoryId;

    /**
     * 项目名称
     */
    private String categoryName;

    /**
     * 品类ID
     */
    private Long subCategoryId;

    /**
     * 品类名
     */
    private String subCategoryName;

    /**
     * 子品类ID
     */
    private Long detailCategoryId;

    /**
     * 子品类名
     */
    private String detailCategoryName;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 品牌名
     */
    private String brandName;

    /**
     * 最小单位
     */
    private String stockUnit;

    /**
     * 最小单位/销售单位换算
     */
    private BigDecimal unitTransfer;

    /**
     * 销售单位
     */
    private String salesUnit;

    /**
     * 管理单位
     */
    private String manageUnit;

    /**
     * 最小单位/管理单位
     */
    private BigDecimal manageStock;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 包装方式
     */
    private String packName;

    /**
     * 是否临时目录
     */
    private Integer bolTemporary;

    /**
     * 国家代码
     */
    private String countryCode;

    /**
     * 厂商代码
     */
    private String companyCode;

    /**
     * 生产厂商
     */
    private String companyName;

    /**
     * 物品的厂商编码
     */
    private String materielCode;

    /**
     * 是否分摊费用
     */
    private Integer bolShare;

    /**
     * 注册证号
     */
    private String registerNo;

    /**
     * 保质期
     */
    private Integer qualityTime;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Field(type = FieldType.Date, pattern = "yyyy-MM-dd HH:mm:ss", format = DateFormat.custom)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Field(type = FieldType.Date, pattern = "yyyy-MM-dd HH:mm:ss", format = DateFormat.custom)
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProducerCode() {
        return producerCode;
    }

    public void setProducerCode(String producerCode) {
        this.producerCode = producerCode;
    }

    public Long getProductSpecId() {
        return productSpecId;
    }

    public void setProductSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
    }

    public String getProductSpecName() {
        return productSpecName;
    }

    public void setProductSpecName(String productSpecName) {
        this.productSpecName = productSpecName;
    }

    public String getProductSpecNameExt() {
        return productSpecNameExt;
    }

    public void setProductSpecNameExt(String productSpecNameExt) {
        this.productSpecNameExt = productSpecNameExt;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Long getDetailCategoryId() {
        return detailCategoryId;
    }

    public void setDetailCategoryId(Long detailCategoryId) {
        this.detailCategoryId = detailCategoryId;
    }

    public String getDetailCategoryName() {
        return detailCategoryName;
    }

    public void setDetailCategoryName(String detailCategoryName) {
        this.detailCategoryName = detailCategoryName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getStockUnit() {
        return stockUnit;
    }

    public void setStockUnit(String stockUnit) {
        this.stockUnit = stockUnit;
    }

    public BigDecimal getUnitTransfer() {
        return unitTransfer;
    }

    public void setUnitTransfer(BigDecimal unitTransfer) {
        this.unitTransfer = unitTransfer;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public String getManageUnit() {
        return manageUnit;
    }

    public void setManageUnit(String manageUnit) {
        this.manageUnit = manageUnit;
    }

    public BigDecimal getManageStock() {
        return manageStock;
    }

    public void setManageStock(BigDecimal manageStock) {
        this.manageStock = manageStock;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public Integer getBolTemporary() {
        return bolTemporary;
    }

    public void setBolTemporary(Integer bolTemporary) {
        this.bolTemporary = bolTemporary;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMaterielCode() {
        return materielCode;
    }

    public void setMaterielCode(String materielCode) {
        this.materielCode = materielCode;
    }

    public Integer getBolShare() {
        return bolShare;
    }

    public void setBolShare(Integer bolShare) {
        this.bolShare = bolShare;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Integer getQualityTime() {
        return qualityTime;
    }

    public void setQualityTime(Integer qualityTime) {
        this.qualityTime = qualityTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryMat that = (InventoryMat) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(matName, that.matName) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(producerCode, that.producerCode) &&
                Objects.equals(productSpecId, that.productSpecId) &&
                Objects.equals(productSpecName, that.productSpecName) &&
                Objects.equals(productSpecNameExt, that.productSpecNameExt) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(subCategoryId, that.subCategoryId) &&
                Objects.equals(subCategoryName, that.subCategoryName) &&
                Objects.equals(detailCategoryId, that.detailCategoryId) &&
                Objects.equals(detailCategoryName, that.detailCategoryName) &&
                Objects.equals(brandId, that.brandId) &&
                Objects.equals(brandName, that.brandName) &&
                Objects.equals(stockUnit, that.stockUnit) &&
                Objects.equals(unitTransfer, that.unitTransfer) &&
                Objects.equals(salesUnit, that.salesUnit) &&
                Objects.equals(manageUnit, that.manageUnit) &&
                Objects.equals(manageStock, that.manageStock) &&
                Objects.equals(imgUrl, that.imgUrl) &&
                Objects.equals(packName, that.packName) &&
                Objects.equals(bolTemporary, that.bolTemporary) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(companyCode, that.companyCode) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(materielCode, that.materielCode) &&
                Objects.equals(bolShare, that.bolShare) &&
                Objects.equals(registerNo, that.registerNo) &&
                Objects.equals(qualityTime, that.qualityTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matName, productId, productName, producerCode, productSpecId, productSpecName, productSpecNameExt, categoryId, categoryName, subCategoryId, subCategoryName, detailCategoryId, detailCategoryName, brandId, brandName, stockUnit, unitTransfer, salesUnit, manageUnit, manageStock, imgUrl, packName, bolTemporary, countryCode, companyCode, companyName, materielCode, bolShare, registerNo, qualityTime, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "InventoryMatEntity{" +
                "id='" + id + '\'' +
                ", matName='" + matName + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", producerCode='" + producerCode + '\'' +
                ", productSpecId=" + productSpecId +
                ", productSpecName='" + productSpecName + '\'' +
                ", productSpecNameExt='" + productSpecNameExt + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", subCategoryId=" + subCategoryId +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", detailCategoryId=" + detailCategoryId +
                ", detailCategoryName='" + detailCategoryName + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", stockUnit='" + stockUnit + '\'' +
                ", unitTransfer=" + unitTransfer +
                ", salesUnit='" + salesUnit + '\'' +
                ", manageUnit='" + manageUnit + '\'' +
                ", manageStock=" + manageStock +
                ", imgUrl='" + imgUrl + '\'' +
                ", packName='" + packName + '\'' +
                ", bolTemporary=" + bolTemporary +
                ", countryCode='" + countryCode + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", materielCode='" + materielCode + '\'' +
                ", bolShare=" + bolShare +
                ", registerNo='" + registerNo + '\'' +
                ", qualityTime=" + qualityTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
