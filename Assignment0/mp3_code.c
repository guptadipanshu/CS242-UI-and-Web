#include <stdio.h>
#include <string.h>
#include <stdlib.h>

//the system uses node IDs 2-8 (inclusive)
int getNodeID()
{
	FILE* readCmd = popen("ifconfig | grep 192 | sed 's/^.*addr://' | sed 's/ .*//'", "r");
	char buf[100];
	fread(buf, 1, 100, readCmd);
	pclose(readCmd);
	return *(strstr(buf, "\n")-1)-'0';
}

//NOTE: this could be made to not take so long to examine all the possible neighbors,
//		if you find that to be an issue ;)
//NOTE: will tell you that you are a neighbor of yourself
void discoverNeighbors(char* retval)
{
	int i;
	for(i=2; i<10; i++)
	{
		char cmdStr[100];
		char buf[1000];
		sprintf(cmdStr, "ping -c 1 -w 1 192.168.56.1%d", i);
		FILE* readCmd = popen(cmdStr, "r");
		fread(buf, 1, 1000, readCmd);
		pclose(readCmd);
		if(strstr(buf, "bytes from"))
			retval[i]=1;
		else
			retval[i]=0;
	}
	sleep(1);
}

//demo
int main()
{
	char neighbors[10];
	int i;
	printf("my id: %d\n", getNodeID());
	discoverNeighbors(neighbors);
	for(i=2; i<10; i++)
		printf("%d\n", neighbors[i]);
}
