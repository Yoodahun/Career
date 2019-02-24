import java.awt.List;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Recipe {
	
	private String foodName;
	private String materialName;
	private String materialNeededQuantity; //바꾸는 것은어떤지??
	private String[] columnNames = {"재료명", "재료량"}; 
	private String[][] rowDatas = {{"양파", "1kg"}, {"식용유", "3g"}};
	
//	private ArrayList<String> foodNameList = new ArrayList<String>();
	private ArrayList<String> materialNameList = new ArrayList<String>();
	private ArrayList<String> materialQuantityList = new ArrayList<String>();
	
	private DefaultTableModel recipeTableModel = new DefaultTableModel(rowDatas, columnNames);
	
	/* 게터, 새터 */
	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	
	}

	
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	

	public void setMaterialNeededQuantity(String materialNeededQuantity) {
		this.materialNeededQuantity = materialNeededQuantity;
	}
	
	
	
	
	public DefaultTableModel recipeAccess() {
	
		return recipeTableModel;
	}
	
	
	void recipeAdd(String materialName, String materialNeededQuantity){
		setMaterialName(materialName);
		setMaterialNeededQuantity(materialNeededQuantity);
		materialNameList.add(materialName);
		materialQuantityList.add(materialNeededQuantity);
		String lists[] = new String[2];
			lists[0] = materialName;
			lists[1] = materialNeededQuantity;
		recipeTableModel.addRow(lists);
		
	}
	
	void recipeModify(String materialNeededQuantity, int x, int y){
		recipeTableModel.setValueAt(materialNeededQuantity, x, y);
		
		
	}
	
	void recipeDelete(String foodName, String materialName, int index){
		if(materialNameList.contains(materialName)) {
			int i = materialNameList.indexOf(materialName);
			
			if(materialQuantityList.indexOf(materialNeededQuantity) == i) {
				materialNameList.remove(i);
				materialQuantityList.remove(i);
				
			}
		}
		
	}

	
	

}
