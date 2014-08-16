/* Unlike Divide-and-Conquer, Dynamic Programming(DP) applies when the subproblems overlap - that is subproblems share subproblems.
  In this context, DP solves each subproblems just once and saves its answer in a table, thereby avoiding the work for recomputing the answer
  every time it solves each subproblem. DP is especially useful for optimization problems, which is to find the best solution among many possible
  solutions. 
  
  -     When developing a dynamic-programming algorithm, we follow a sqeuence of four steps:

        1) Characterize the stucture of an optimal solution.
        2) Recursively define the value of an optimal solution.
        3) Compute the value of an optimal solution, typically in a bottom-up fashion.
        4) Construct an optimal solution from computed information.

  -     Rod Cutting  

        The rod-cutting problem is the following. Given a rod of length n inches and a
        table of prices pi for i = 1,2...,n, determine the maximum revenue r n obtain-
        able by cutting up the rod and selling the pieces.

        If an optimal solution cuts the rod into k pieces, for some 1 <= k <= n, then an optimal decomposition
        
        n = i1 + i2 + ... + ik

        of the rod into pieces of length i1, i2, ..., ik provides maximum corresponding revenue

        rn = pi1 + pi2 + ... + pik




*/ 
