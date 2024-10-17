package main;
import services.InsuranceServiceImpl;
import myexceptions.PolicyNotFoundException;
import entities.Policy;

import java.util.Collection;
import java.util.Scanner;

public class MainModule {

	public static void main(String[] args) throws PolicyNotFoundException {
		// TODO Auto-generated method stub
		{
	        InsuranceServiceImpl insuranceService = new InsuranceServiceImpl();
	        Scanner scanner = new Scanner(System.in);
	        while (true) {
	            System.out.println("\n1. Create Policy");
	            System.out.println("2. Retrieve Policy");
	            System.out.println("3. Get All Policies");
	            System.out.println("4. Update Policy");
	            System.out.println("5. Delete Policy");
	            System.out.println("6. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();
	            try {
	            	switch (choice) {
	            	case 1:
	            		System.out.println("Enter Policy ID:");
	            		int policyId = scanner.nextInt();
                        scanner.nextLine(); 
	            		System.out.println("Enter Policy Name:");
	            		String policyName = scanner.nextLine();

	            		System.out.println("Enter Policy Type:");
	            		String policyType = scanner.nextLine();
                    
	            		Policy newPolicy = new Policy(policyId, policyName, policyType);
	            
	            		insuranceService.createPolicy(newPolicy);
	            		System.out.println("Policy created successfully.");
	            		break;
	            	case 2:
	            		System.out.println("Enter Policy ID to retrieve:");
                        int retrieveId = scanner.nextInt();
                        Policy retrievedPolicy = insuranceService.getPolicy(retrieveId);

                        if (retrievedPolicy == null) {
                            throw new PolicyNotFoundException("Policy not found");
                        }
                        System.out.println("Retrieved Policy: " + retrievedPolicy);
                        break;
                        
	            	case 3:
                        Collection<Policy> allPolicies = insuranceService.getAllPolicies();
                        System.out.println("All Policies:");
                        for (Policy policy : allPolicies) {
                            System.out.println(policy);
                        }
                        break;

	            
	            	case 4:
                        System.out.println("Enter Policy ID to update:");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();  // Consume newline

                        System.out.println("Enter new Policy Name:");
                        String newPolicyName = scanner.nextLine();

                        System.out.println("Enter new Policy Type:");
                        String newPolicyType = scanner.nextLine();

                        Policy updatedPolicy = new Policy(updateId, newPolicyName, newPolicyType);
                        boolean isUpdated = insuranceService.updatePolicy(updatedPolicy);

                        if (isUpdated) {
                            System.out.println("Policy updated successfully.");
                        } else {
                            System.out.println("Failed to update policy.");
                        }
                        break;

	            
	            	case 5:
                        System.out.println("Enter Policy ID to delete:");
                        int deleteId = scanner.nextInt();
                        boolean isDeleted = insuranceService.deletePolicy(deleteId);

                        if (isDeleted) {
                            System.out.println("Policy deleted successfully.");
                        } else {
                            System.out.println("Failed to delete policy.");
                        }
                        break;
	            	case 6:
                        System.out.println("Thank You");
                        scanner.close();
                        System.exit(0);
                        break;
	            	default:
                        System.out.println("Invalid choice. Please try again.");
	            	}
	            }
//	        	catch (PolicyNotFoundException e) {
//	        		System.out.println(e);
//	        	}
	            	catch (Exception e) {
	            		System.out.println(e);
	            	}
	        
	        }
		}
	}
}
		
