import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Material {
	
	private String materialName;
	private String materialQuantity; //변경을 하는것은?
	private	ArrayList<String> materialNameList = new ArrayList<String>();
	private ArrayList<String> materialQuantityList = new ArrayList<String>();
	private String[] columnNames = {"재료명", "재료량"};
	private String[][] rowDatas = {};
	private DefaultTableModel materialTableModel = new DefaultTableModel(rowDatas, columnNames);
	
	PosSystem posSystem;
	
	/* 게터 새터 */
	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialQuantity() {
		return materialQuantity;
	}

	public void setMaterialQuantity(String materialQuantity) {
		this.materialQuantity = materialQuantity;
	}
	
	
	

	void materialModify(String materialName,String materialQuantity, int x, int y){
		if(materialQuantityList.contains(materialName) == true) {
			int i = materialQuantityList.indexOf(materialName);
			
			materialQuantityList.add(i, materialQuantity);
			
			
			}
			
		}
		
	

	public void materialAdd(String materialName, String materialQuantity){

		materialNameList.add(materialName);
		materialQuantityList.add(materialQuantity);
		
		String settingMaterial[] = new String[2];
		settingMaterial[0] = materialName;
		settingMaterial[1] = materialQuantity;
		materialTableModel.addRow(settingMaterial);
	
	}
	
	void materialDelete(String materialName) {
		
		if(materialNameList.contains(materialName)) {
			int i = materialNameList.indexOf(materialName);
			
			if(materialQuantityList.indexOf(materialQuantity) == i) {
				materialNameList.remove(i);
				materialQuantityList.remove(i);
				
			}
		}
		
		}
		
		
		
		
	
	
	public DefaultTableModel materialAccess(){
		
		return materialTableModel;
		
	}



}
