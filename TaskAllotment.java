/*
* @Author: pankajgoyal7
* @Date:   2018-07-05 00:12:08
* @Last Modified by:   pankajgoyal7
* @Last Modified time: 2018-07-05 01:00:09
*/
import java.util.*;

class TaskAllotment{
	
	Deque<Integer> queue = new ArrayDeque<>();
	
	Map<Integer,Integer> allotedTask = new HashMap<Integer,Integer>();

	int[] orgEmployeeCount;
	
	int[] orgCurrentEmployeeCount;

	TaskAllotment(int orgCount){
		orgEmployeeCount = new int[orgCount];
		orgCurrentEmployeeCount = new int[orgCount];
	}
	

	private void allotTaskToOrganisation(int taskId){
		int orgNo = queue.removeFirst();
		if(orgCurrentEmployeeCount[orgNo] > 0){
			orgCurrentEmployeeCount[orgNo] = orgCurrentEmployeeCount[orgNo] - 1;
			allotedTask.put(taskId,orgNo);
			System.out.println(taskId+" alloted to "+orgNo);
		}else{
			System.out.println("No Employee is free ");
			System.exit(1);
		}
		queue.offerLast(orgNo);
	}

	private void clearTask(Integer taskId){
		int orgNo = (int)allotedTask.get(taskId);
		if(orgCurrentEmployeeCount[orgNo] + 1 <= orgEmployeeCount[orgNo]){
			orgCurrentEmployeeCount[orgNo] = orgCurrentEmployeeCount[orgNo] + 1;
			System.out.println("Task "+taskId+" Cleared from "+orgNo);
		}
	}

	private void addOrganisation(int index,int count){
		queue.offerLast(index);
		orgEmployeeCount[index] = count;
		orgCurrentEmployeeCount[index] = count;
		System.out.println("addded ... "+index);
	}

	public static void main(String[] args) {

		System.out.println("How Many Organisations");
		Scanner sc = new Scanner(System.in);
		
		int totatCount = sc.nextInt();
		TaskAllotment task = new TaskAllotment(totatCount);

		int totalOrgs = 0;
		System.out.println("Enter Empoloyee for "+totatCount+" Organisations.");

		while(totalOrgs < totatCount && sc.hasNext()){
			task.addOrganisation(totalOrgs,sc.nextInt());
			totalOrgs += 1;
		}

		System.out.println("\nAllot/Clear Task");
		System.out.println("1 To Add Task");
		System.out.println("2 To Clear Task");

		int c = 0;
		while(sc.hasNext()){
			switch(sc.nextInt()){
				case 1:
					task.allotTaskToOrganisation(c);
					c = c+1;

					System.out.println("Allot/Clear Task");
					System.out.println("1 To Add Task");
					System.out.println("2 To Clear Task");
					break;		
				case 2:
					System.out.println("Enter Task Id");
					task.clearTask(sc.nextInt());

					System.out.println("Allot/Clear Task");
					System.out.println("1 To Add Task");
					System.out.println("2 To Clear Task");
					break;
			}
			
			totalOrgs++;
		}
	}
}