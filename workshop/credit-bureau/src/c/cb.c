/**
 * Este aplicacion representa a una entidad crediticia la cual
 * concentra todos los creditos otorgados a los clientes de
 * diferentes entidades.
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <winsock2.h>
#include <unistd.h>

#define PORT 3550 /* El puerto que será abierto */
#define BACKLOG 2 /* El número de conexiones permitidas */

void searchRFC ( char* rfc, int sock){
	FILE* file;
	file = fopen("Loans.txt","r");
	char line[100];
	char* line_r;
	char rfcf[11];
	boolean find = FALSE;
	while( fgets(line,100,file) != NULL){
		line_r = line+4;
		strncpy(rfcf,line_r,10);
		//printf("rfc file: %s\n",rfcf);
		if ( strncmp(rfcf,rfc,10) == 0){
			printf("%s %d\n",line,strlen(line));
			send(sock,line,strlen(line),0);
			find = TRUE;
		}
	}
	fclose(file);
	if (find == FALSE){
		send(sock,"Not Found\n",10,0);
	}
	rfc = NULL;
	//send(sock,"END OF TRANSMISSION\n",20,0);
	return;
}

void add_loan(char* rfc, int sock){
	FILE* file;
	file = fopen("Loans.txt","a+");
	fprintf(file,"\n%s",rfc);
	printf("%d",strlen(rfc));
	fclose(file);
}

void closeLoan(char* credito,int sock){
	FILE* file;
	file = fopen("Loans.txt","r+");
	char line[200];
	char* line_r;
	char rfcf[11];
	boolean find = FALSE;
	char DELIMITER = '|';
	fpos_t pos;
	while( fgets(line,200,file) != NULL){
		//strncpy(rfcf,line_r,10);
		//if ( strncmp(rfcf,rfc,10) == 0){
		//	printf("%s %d\n",line,strlen(line));
		//	send(sock,line,strlen(line),0);
		//	find = TRUE;}
		short count = 0;
		int i;
		char *b_code, *rfc, *ammount, *date;
		int lineSize = strlen(line);
		for(i=0; i<lineSize;i++){ //split line readed from file
			b_code = line; //b_code
			if (line[i] == DELIMITER){
				count++;
				line[i] = '\0';
				switch(count){
					case 1:	//rfc
						rfc = &line[i+1];
						break;
					case 4:	//amount
						ammount = &line[i+1];
						break;
					case 5:  //date
						date = &line[i+1];
						break;
				}	
			}
		}
		if( strncmp(credito,b_code,strlen(b_code)) == 0){  //check if bank_code are equal
			char *rfcReceived, *ammountReceived, *dateReceived;
			short count = 0;
			int creditoLength = strlen(credito);
			for(i=0;i<creditoLength; i++){
				if(credito[i] == DELIMITER){
					credito[i] = '\0';
					count++;
					switch(count){
						case 1:
							rfcReceived = &credito[i+1];
							break;
						case 2:
							ammountReceived = &credito[i+1];
							break;
						case 3:
							dateReceived = &credito[i+1];
							break;
					}
				}
			}
			printf("received r:%s  a:%s  d:%s\n",rfcReceived,ammountReceived,dateReceived);
			if(strcmp(rfc,rfcReceived) == 0)
				if(strcmp(ammount,ammountReceived) == 0)
					if(strcmp(date,dateReceived) == 0){
						 fgetpos( file, &pos );  //pos - 3 
						 pos = pos -3;
						 fsetpos(file, &pos );
						printf( "Posicion del fichero: %d\n", pos);
						fprintf(file,"N");
						send(sock,"LOAN CLOSE\n",11,0);
						fflush(file);
						fclose(file);
						return;
					}
			}
		printf("b:%s  r:%s  a:%s  d:%s\n",b_code,rfc,ammount,date);
		
	}
	fclose(file);
	if (find == FALSE){
		send(sock,"Not Found\n",10,0);
	}
	return;
}
void processPetition(char* rfc, int sock){
	switch (rfc[0]){
		case 'f':
			searchRFC(rfc+1, sock);
			break;
		case 'a':
			add_loan(rfc+1, sock);
			break;
		case 'c':
			closeLoan(rfc+1, sock);
			break;
		default:
			send(sock,"Operacion no valida\n",20,0);
	}
}

void doprocessing (int sock)
{
    int n;
    char buffer[256];

    memset(&(buffer), '0', 256);
    int recvMsgSize;
    
    /* Receive message from client */
    if ((recvMsgSize = recv(sock, buffer, 256, 0)) < 0)
        perror("ERROR reading to socket");

    /* Send received string and receive again until end of transmission */
    while (recvMsgSize > 0)      /* zero indicates end of transmission */
    {
        /* Echo message back to client */
       //if (send(sock, buffer, recvMsgSize, 0) != recvMsgSize)
         // perror("ERROR writing to socket");
        /*search RFC and send result*/
        
        buffer[recvMsgSize] = '\0'; //agregar fin de cadena
        processPetition(buffer,sock);
        /*
        char* result = searchRFC(buffer);
        printf("resultado: %s\nsize: %d\n",result,strlen(result));
        char resultado[100];
        strcmp(resultado,result);
        printf("resultado: %s\n",resultado);
        if(send(sock,result,strlen(result),0) != recvMsgSize)
			perror("Error sending rfc");
			*/
			
        int i;
        
        printf("Message received: ");
        for(i=0;i<recvMsgSize;i++)
			printf("%c",buffer[i]);
		printf("\n");

        /* See if there is more data to receive */
        if ((recvMsgSize = recv(sock, buffer, 256, 0)) < 0)
            perror("ERROR reading to socket");
            
        //printf("%s",buffer);
    }

    closesocket(sock);    /* Close client socket */
}

BOOL initW32() 
{
		WSADATA wsaData;
		WORD version;
		int error;
		
		version = MAKEWORD( 2, 0 );
		
		error = WSAStartup( version, &wsaData );
		
		/* check for error */
		if ( error != 0 )
		{
		    /* error occured */
		    return FALSE;
		}
		
		/* check for correct version */
		if ( LOBYTE( wsaData.wVersion ) != 2 ||
		     HIBYTE( wsaData.wVersion ) != 0 )
		{
		    /* incorrect WinSock version */
		    WSACleanup();
		    return FALSE;
		}	
}

int main()
{

	 initW32(); /* Necesaria para compilar en Windows */ 
	 	
   int fd, fd2; /* los descriptores de archivos */

   /* para la información de la dirección del servidor */
   struct sockaddr_in server;

   /* para la información de la dirección del cliente */
   struct sockaddr_in client;

   unsigned int sin_size;

   pid_t pid;

   /* A continuación la llamada a socket() */
   if ((fd=socket(AF_INET, SOCK_STREAM, 0)) == -1 ) {
      printf("error en socket()\n");
      exit(-1);
   }

   server.sin_family = AF_INET;

   server.sin_port = htons(PORT);

   //server.sin_addr.s_addr = INADDR_ANY;
   server.sin_addr.s_addr = inet_addr("127.0.0.1");
   /* INADDR_ANY coloca nuestra dirección IP automáticamente */
   

   //bzero(&(server.sin_zero),8);
   memset(&(server.sin_zero), '0', 8);
   /* escribimos ceros en el reto de la estructura */


   /* A continuación la llamada a bind() */
   if(bind(fd,(struct sockaddr*)&server, sizeof(struct sockaddr))==-1) {
      printf("error en bind() \n");
      exit(-1);
   }

   if(listen(fd,BACKLOG) == -1) {  /* llamada a listen() */
      printf("error en listen()\n");
      exit(-1);
   }

   while(1) {
      sin_size=sizeof(struct sockaddr_in);
      /* A continuación la llamada a accept() */
      if ((fd2 = accept(fd,(struct sockaddr *)&client, &sin_size))==-1) {
         printf("error en accept()\n");
         exit(-1);
      }

      printf("Se obtuvo una conexión desde %s\n", inet_ntoa(client.sin_addr) );
      /* que mostrará la IP del cliente */

      //send(fd2,"Bienvenido a mi servidor.\n",25,0);
      /* que enviará el mensaje de bienvenida al cliente */
      
      doprocessing(fd2);

   } /* end while */
}

