#include "string.h"
#include "stdio.h"

char linebuffer[1000];

int main()
{
  int count=0;
  while (fgets(linebuffer,sizeof(linebuffer),stdin)!=0) {
	++count;
  }
  printf("%d\n",count);
  return 0;
}
