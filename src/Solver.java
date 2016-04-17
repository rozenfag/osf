import java.math.*;
public class Solver {

	
	private double[][][] input_probabilities = {{{0.33,0.5},{0.66,0.75}},
												{{0.25,0.5},{0.33,0.66}},
												{{0.25,0.66},{0.5,0.75}}
												};
	private int[] c = {1,2,3};
	private int[] b = {3,1,2};
	private int[][] local_decision_spaces = {{0,0,0},{0,0,1},{0,1,0},{0,1,1},{1,0,0},{1,0,1},
			{1,1,0},{1,1,1}};
	private int[][] states = {{0,0,2},{0,1,1},{0,2,0},{1,0,1},{1,1,0},{2,0,0}};						
	/*
	public Solver(double[][][] input_probabilities,int[] c,int[] b,int[][] local_descision_spaces,int[][] states){
		this.input_probabilities = input_probabilities;
		this.c = c;
		this.b = b;
		this.local_decision_spaces = local_descision_spaces;
		this.states = states;
	}
	*/
	// R delta
	private double[] totalCostFunction(double[][] ergodic_distributions, 
									   double[][] step_costs, int state_variable_size){
		double[] total_costs = new double[ergodic_distributions.length];
		for(int i=0;i<ergodic_distributions.length;i++){
			for(int j=0;j<state_variable_size;j++){
				total_costs[i]+=ergodic_distributions[i][j]*step_costs[i][j];
			}
		}
		return total_costs;
	}
	// r(x,u)
	private double[][] stepCostFunction(int[] c,int[] b,int[][] states,
										int[][] localDecisionSpaces){
		double[][] stepCost = new double[localDecisionSpaces.length][states.length];
		for(int k=0;k<states.length;k++){
		for(int i=0;i<localDecisionSpaces.length;i++){
			//for(int k=0;k<states.length;k++){
				for(int j=0;j<c.length;j++){
					stepCost[i][k]+=(c[j]-localDecisionSpaces[i][j])*states[k][j]+localDecisionSpaces[i][j]*b[j];	
				}	
			}
		}
		
		return stepCost;
	}
	
	public double[][] egrodicDistributionFinder(double[][][] input_probabilities,
												double[][] states, int size){
		double[][] ergodic_distributions = new double[states.length][size];
		
		return ergodic_distributions; 
	}
	
	public static void main(String[] args) {
		Solver s = new Solver();
		// r(x,u) - costs
		double[][] cost = s.stepCostFunction(s.c, s.b, s.states, s.local_decision_spaces);
		
	    /*for(int i = 0;i<s.states.length;i++){
			System.out.println(cost[0][i]);
		}
		*/
		for(int i = 0;i<s.local_decision_spaces.length;i++){
			for(int j=0;j<s.states.length;j++){
				System.out.print((int)cost[i][j]+" ");
			}
			System.out.println("\n");
		}
		double[][] ergodic_distributions = {{0.1034483,0.2758621,0.1551724,0.2068966,0.2068966,0.05172414},{3/29,8/29,9/58,6/29,6/29,3/58},{3/29,8/29,9/58,6/29,6/29,3/58},{3/29,8/29,9/58,6/29,6/29,3/58},{3/29,8/29,9/58,6/29,6/29,3/58},{3/29,8/29,9/58,6/29,6/29,3/58},{3/29,8/29,9/58,6/29,6/29,3/58},{3/29,8/29,9/58,6/29,6/29,3/58}};
		
		for(int i = 0;i<s.local_decision_spaces.length;i++){
			for(int j=0;j<s.states.length;j++){
				System.out.print(ergodic_distributions[i][j]+" ");
			}
			System.out.println("\n");
		}
		System.out.println(ergodic_distributions.length);
		double[] total_cost = s.totalCostFunction(ergodic_distributions, cost, s.states.length);
		System.out.println(total_cost[0]);
	}
}


