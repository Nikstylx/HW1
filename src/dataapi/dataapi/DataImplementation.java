package dataapi;
public class DataImplementation{
	private Data dataStore;
	
	public DataImplementation(Data dataStore) {
		this.setDataStore(dataStore);
	}
	public Object fetchData(String query) {
		return null;
	}
	public boolean saveData(Object data) {
		return false;
	}
	public boolean deleteData(String dataId) {
		return false;
	}
	public boolean updateData(String dataID, Object data) {
		return false;
	}
	public Data getDataStore() {
		return dataStore;
	}
	public void setDataStore(Data dataStore) {
		this.dataStore = dataStore;
	}
}