package Entity;

public class CompanyEmployee {
	
	private int EmployeeNumber;
	private String job;
	private String permissions;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + EmployeeNumber;
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((permissions == null) ? 0 : permissions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyEmployee other = (CompanyEmployee) obj;
		if (EmployeeNumber != other.EmployeeNumber)
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (permissions == null) {
			if (other.permissions != null)
				return false;
		} else if (!permissions.equals(other.permissions))
			return false;
		return true;
	}
	
	
	public CompanyEmployee (int EmployeeNumber, String job,String Permissions)
	{
		this.EmployeeNumber=EmployeeNumber;
		this.job=job;
		this.permissions=Permissions;
	}
	
	public void setEmployeeNumber(int EmployeeNumberToInsert ) {
        this.EmployeeNumber=EmployeeNumberToInsert;
    }
	
	public int getCompanyEmployee () {
        return this.EmployeeNumber;
    }
	
	public void setJob(String JobToInsert ) {
        this.job=JobToInsert;
    }
	public String getJob (){
        return this.job;
    }
	
	public void setPermissions(String PermissionsToInsert ) {
        this.permissions=PermissionsToInsert;
    }
	public String getPermission (){
        return this.permissions;
    }
	
	
	
	

}
