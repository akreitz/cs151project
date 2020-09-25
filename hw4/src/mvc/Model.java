package mvc;

public abstract class Model extends Bean {
	
	private String fileName = null;
	private Boolean unsavedChanges = false;
	
	public String getFileName() {
		return fileName;
	}
	
	public Boolean getUnsavedChanges() {
		return unsavedChanges;
	}
	
	public void setFileName(String newName) {
		this.fileName = newName;
	}
	
	public void setUnsavedChanges(Boolean updateChanges) {
		this.unsavedChanges = updateChanges;
	}
	
	public void changed() {
		//Utilities.inform("CHANGE MADE");
		unsavedChanges = true;
		firePropertyChange(null, null, null);
	}
	
	public void copy(Model other) {
		this.fileName = other.fileName;
		this.unsavedChanges = other.unsavedChanges;
	}

}
