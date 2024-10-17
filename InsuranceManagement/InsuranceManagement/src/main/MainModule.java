package main;
import services.InsuranceServiceImpl;
import myexceptions.PolicyNotFoundException;
import entities.Policy;

import java.util.Collection;

public class MainModule {

	public static void main(String[] args) throws PolicyNotFoundException {
		// TODO Auto-generated method stub
		{
	        InsuranceServiceImpl insuranceService = new InsuranceServiceImpl();

	        try {
	            
	            Policy newPolicy = new Policy(0, "Health Insurance", "Health");
	            
	            insuranceService.createPolicy(newPolicy);
	            System.out.println("Policy created successfully.");

	            
	            Policy retrievedPolicy = insuranceService.getPolicy(1);  
	            System.out.println("Retrieved Policy: " + retrievedPolicy);

	            
	            Collection<Policy> allPolicies = insuranceService.getAllPolicies();
	            System.out.println("All Policies:");
	            for (Policy policy : allPolicies) {
	                System.out.println(policy);
	            }

	            
	            Policy updatedPolicy = new Policy(1, "Updated Health Insurance", "Health");
	            boolean isUpdated = insuranceService.updatePolicy(updatedPolicy);
	            if (isUpdated) {
	                System.out.println("Policy updated successfully.");
	            } else {
	                System.out.println("Failed to update policy.");
	            }

	            
	            boolean isDeleted = insuranceService.deletePolicy(3);  
	            if (isDeleted) {
	                System.out.println("Policy deleted successfully.");
	            } else {
	                System.out.println("Failed to delete policy.");
	            }
	        }
//	        catch (PolicyNotFoundException e) {
//	        	System.out.println(e);
//	        }
	        catch (Exception e) {
	           System.out.println(e);
	        }
	        
	        
		}
	}
}
		
