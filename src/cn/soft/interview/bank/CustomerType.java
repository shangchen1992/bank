/**
 * 
 */
package cn.soft.interview.bank;

/**
 * @author franklv
 * @Desc 客户类型枚举
 * @Date 2019年4月15日 下午5:26:50
 */
public enum CustomerType {
	COMMON,FAST,VIP;
	public String toString(){
		switch(this){
		case COMMON:
			return "普通";
		case FAST:
			return "快速";
		case VIP:
			return "VIP";
		}
		return null;
	}
}
