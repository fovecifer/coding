/////////////////////////////////////////////////////////////////////////////////////////////
// Standard Input
// int a;
// float b, c;
// double d, e, f;
// char g;
// char var[256];
// long long AB;
// scanf("%d", &a);                      
// scanf("%f %f", &b, &c);               
// scanf("%lf %lf %lf", &d, &e, &f);     
// scanf("%c", &g);                      
// scanf("%s", &var);                    
// scanf("%lld", &AB);                   
/////////////////////////////////////////////////////////////////////////////////////////////
// Standard Output
// int a = 0;                            
// float b = 1.0, c = 2.0;               
// double d = 3.0, e = 0.0; f = 1.0;
// char g = 'b';
// char var[256] = "ABCDEFG";
// long long AB = 12345678901234567L;
// printf("%d", a);                      
// printf("%f %f", b, c);                
// printf("%lf %lf %lf", d, e, f);       
// printf("%c", g);                      
// printf("%s", var);                    
// printf("%lld", AB);                   
/////////////////////////////////////////////////////////////////////////////////////////////
#include <stdio.h>

int main(void)
{
	int test_case;
	int T;
	/*
	   freopen function below opens input.txt file in read only mode, and afterward,
	   the program will read from input.txt file instead of standard(keyboard) input.
	   To test your program, you may save input data in input.txt file,
	   and use freopen function to read from the file when using scanf function.
	   You may remove the comment symbols(//) in the below statement and use it.
	   But before submission, you must remove the freopen function or rewrite comment symbols(//).
	 */
	// freopen("input.txt", "r", stdin);
	/*
	   If you remove the statement below, your program's output may not be recorded
	   when your program is aborted due to the time limit.
	   For safety, please use setbuf(stdout, NULL); statement.
	 */
	setbuf(stdout, NULL);
	scanf("%d", &T);
	/*
	   Read each test case from standard input.
	*/
	for (test_case = 1; test_case <= T; ++test_case)
	{
		/////////////////////////////////////////////////////////////////////////////////////////////
		/*
			Please, implement your algorithm from this section.
		*/
		/////////////////////////////////////////////////////////////////////////////////////////////
		// Print the answer to standard output(screen). 
		printf("#%d", test_case);
	}
	return 0; //Your program should return 0 on normal termination.
}
