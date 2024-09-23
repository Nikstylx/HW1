package clientapi;

import computeapi.CompAPI;
import dataapi.Data;

class ClientImplemation{
	private Data dataStore;
	private CompAPI computeApi;
	
	public ClientImplemation(Data dataStore, CompAPI computeApi) {
		this.setDataStore(dataStore);
		this.setComputeApi(computeApi) ;
	}

	public Data getDataStore() {
		return dataStore;
	}

	public void setDataStore(Data dataStore) {
		this.dataStore = dataStore;
	}

	public CompAPI getComputeApi() {
		return computeApi;
	}

	public void setComputeApi(CompAPI computeApi) {
		this.computeApi = computeApi;
	}
	 public String getClientInfo(String clientId) {
		 return null;
	 }
	 public boolean createClient(String clientData){
		 return false;
	 }
	 public boolean updateClient(String clientId, String clientData) {
		 return false;
	 }
	 public boolean deleteClient(String clientId){
		 return false;
	 }
}
