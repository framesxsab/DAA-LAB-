#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

void crc_division(char *dividend, const char *divisor, char *remainder) {
    int dividend_len = strlen(dividend);
    int divisor_len = strlen(divisor);

    char temp_dividend[100];
    strcpy(temp_dividend, dividend);

    for (int i = 0; i <= dividend_len - divisor_len; i++) {
        
        if (temp_dividend[i] == '1') {
            for (int j = 0; j < divisor_len; j++) {
                if (temp_dividend[i + j] == divisor[j]) {
                    temp_dividend[i + j] = '0';
                } else {
                    temp_dividend[i + j] = '1';
                }
            }
        }
    }

    for (int i = 0; i < divisor_len - 1; i++) {
        remainder[i] = temp_dividend[dividend_len - (divisor_len - 1) + i];
    }
    remainder[divisor_len - 1] = '\0';
}


void problem_one() {
 
    char msg[20], remainder[20], transmitted_frame[50];
    const char *generator = "1011"; 
    int generator_len = strlen(generator);


    const char *delimiter = "0111110";


   
    printf("Sender Side (CRC & Bit Stuffing)\n");
 

    printf("Enter an 8-bit binary message (e.g., 10101010): ");
    scanf("%s", msg);


    char augmented_msg[30];
    strcpy(augmented_msg, msg);
    for (int i = 0; i < generator_len - 1; i++) {
        strcat(augmented_msg, "0");
    }
    printf("1. Augmented message (with %d zeros): %s\n", generator_len - 1, augmented_msg);

    crc_division(augmented_msg, generator, remainder);
    printf("   CRC Remainder: %s\n", remainder);

    
    char payload[30];
    strcpy(payload, msg);
    strcat(payload, remainder);
    printf("   Payload (Data + CRC): %s\n", payload);

    char stuffed_payload[50] = "";
    int consecutive_ones = 0;
    for (int i = 0; i < strlen(payload); i++) {
        char bit = payload[i];
        strncat(stuffed_payload, &bit, 1);
        if (bit == '1') {
            consecutive_ones++;
        } else {
            consecutive_ones = 0;
        }


        if (consecutive_ones == 5) {
            strcat(stuffed_payload, "0");
            consecutive_ones = 0;
        }
    }
    printf("2. Bit-stuffed payload: %s\n", stuffed_payload);

    
    strcpy(transmitted_frame, delimiter);
    strcat(transmitted_frame, stuffed_payload);
    strcat(transmitted_frame, delimiter);
    printf("3. Transmitted frame: %s\n", transmitted_frame);

  
    printf(" Receiver Side (CRC & Bit De-stuffing)\n");
    

    
    int error_choice;
    printf("Simulate a transmission error? (1 for yes, 0 for no): ");
    scanf("%d", &error_choice);
    if (error_choice == 1) {
        srand(time(0));
        int error_pos = rand() % strlen(transmitted_frame);
        transmitted_frame[error_pos] = (transmitted_frame[error_pos] == '0') ? '1' : '0';
        printf("Simulating error at position %d. Received frame: %s\n", error_pos, transmitted_frame);
    } else {
        printf("No transmission error simulated. Received frame: %s\n", transmitted_frame);
    }
    
    
    char received_payload[50];
    strncpy(received_payload, transmitted_frame + strlen(delimiter), strlen(transmitted_frame) - 2 * strlen(delimiter));
    received_payload[strlen(transmitted_frame) - 2 * strlen(delimiter)] = '\0';
    printf("1. Received payload (without delimiters): %s\n", received_payload);

    
    char destuffed_payload[50] = "";
    consecutive_ones = 0;
    for (int i = 0; i < strlen(received_payload); i++) {
        char bit = received_payload[i];
        strncat(destuffed_payload, &bit, 1);
        if (bit == '1') {
            consecutive_ones++;
        } else {
            consecutive_ones = 0;
        }
        
        if (consecutive_ones == 5 && received_payload[i + 1] == '0') {
            i++; 
            consecutive_ones = 0;
        }
    }
    printf("2. De-stuffed payload: %s\n", destuffed_payload);

    
    char check_remainder[20];
    crc_division(destuffed_payload, generator, check_remainder);
    

    int correct = 1;
    for (int i = 0; i < generator_len - 1; i++) {
        if (check_remainder[i] != '0') {
            correct = 0;
            break;
        }
    }

    if (correct) {
        printf("3. CRC Remainder: %s\n", check_remainder);
        printf("   Message received correctly!\n");
    } else {
        printf("3. CRC Remainder: %s\n", check_remainder);
        printf("   Error detected in the message!\n");
    }
}

int main() {
    problem_one();
    return 0;
}
