# Backtracking

> Backtracking is a general algorithm for finding all (or some) solutions to some computational problems (notably Constraint satisfaction problems or CSPs), which incrementally builds candidates to the solution and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot lead to a valid solution. 

Conceptually, one can imagine the procedure of backtracking as the tree traversal. Starting from the root node, one sets out to search for solutions that are located at the leaf nodes. Each intermediate node represents a partial candidate solution that could potentially lead us to a final valid solution. At each node, we would fan out to move one step further to the final solution, i.e. we iterate the child nodes of the current node. Once we can determine if a certain node cannot possibly lead to a valid solution, we abandon the current node and backtrack to its parent node to explore other possibilities. It is due to this backtracking behaviour, the backtracking algorithms are often much faster than the brute-force search algorithm, since it eliminates many unnecessary exploration. 


Backtracking template

    def backtrack(candidate):
        if find_solution(candidate):
            output(candidate)
            return
        
        # iterate all possible candidates.
        for next_candidate in list_of_candidates:
            if is_valid(next_candidate):
                # try this partial candidate solution
                place(next_candidate)
                # given the candidate, explore further.
                backtrack(next_candidate)
                # backtrack
                remove(next_candidate)
