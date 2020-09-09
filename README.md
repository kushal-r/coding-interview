<<<<<<< HEAD
# coding-interview
=======
**Combinatorics (From n choose k) & Probability**

[Combinatorics(Factorial/Permute/Choose)](https://www.youtube.com/watch?v=8RRo6Ti9d0U&ab_channel=SpoonfulofMaths)
<br/>
<br/>
Factorial: Number of ways to re-arrange n objects<br/>
`n*(n-1)*(n-2)*....*2*1` different ways = n!<br/>
The 1st object can be arranged in n different ways, the second object can be arranged in (n-1) ways as 1 position has already been blocked by the 1st object...and so forth for the rest of the objects, i.e. 3rd object can be re arranged in (n-2) ways, etc.
<br/>
<br/>
Permutation: Number of ways to re-arrange r objects from a list of n objects<br/>
Number of ways to re arrange n objects = `n*(n-1)*(n-2)*....*2*1 = n!`<br/>
Now we don't need to re- arrange all the objects we only need to re-arrange r objects.
So, we have to discard all the other re-arrangements, except the r that we want = `n*(n-1)*(n-2)*...*r*...*2*1/(r-1*r-2*...2*1)` = `n!/(n-r)!` = nPr
<br/>
<br/>
Combination(Choose): Number of ways to select r objects from  a list of n objects, where order does not matter.<br/>
nPr --> Number of ways to select r objects and re-arrange them.<br/>
Now we want to discard the ordering information and simply choose the objects.<br/>
So, to re-arrange r objects we have r! ways<br/>
Finally, removing the ordering information we have to discard r! ways from the re-arrangement<br/>
`nPr/r! = n!/(n-r)!*r!` = nCr ways.



>>>>>>> Initial commit
