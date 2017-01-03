package cn.shuai.tissot.dto;

import cn.shuai.tissot.entity.SuccessRecord;
import cn.shuai.tissot.enums.ProductStatEnum;

/**
 * Created by shuai on 2016/12/31.
 */
public class ProductExecution {

    private long productId;

    private int state;

    private String stateInfo;

    private SuccessRecord successRecord;

    public ProductExecution(long productId, ProductStatEnum stateEnum, SuccessRecord successRecord) {
        this.productId = productId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successRecord = successRecord;
    }

    public ProductExecution(long productId, ProductStatEnum stateEnum) {
        this.productId = productId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessRecord getSuccessRecord() {
        return successRecord;
    }

    public void setSuccessRecord(SuccessRecord successRecord) {
        this.successRecord = successRecord;
    }

    @Override
    public String toString() {
        return "ProductExecution{" +
                "productId=" + productId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successRecord=" + successRecord +
                '}';
    }
}
