/*
 * Program title:                Paint Pro
 * Author:                       Rachael Mohl
 * Last Updated (Version 1.0):   Sunday, June 17, 2018
 * Last Updated (Version 2.0):   Wednesday, April 29, 2020
 * Instructor/course:            Wei Qin/ICS3U
 */


/* Imports: */

import java.text.DecimalFormat;
import processing.sound.*;


/* Main Variables: */

/* Image and sound file varibles: */
PFont f;
PImage img;
SoundFile file;
SoundFile fileChris;

String show = "titlescreen";            //Determines what is displayed on screen



double money = 0;    /* can be character class variable */




//Character Select Variables:

String username = "";
boolean noUsername = false;              //Prevents user from having no username

//User Skin Colour
int s = 0;                                                                                            //Used to make Character Select screen functions easier
color []skinChoices = {#ffdbac,#f1c27d,#e0ac69,#c68642,#8d5524,#ef6a65,#ed3d51,#93d2c7,#331748};      //User options
color skinColour = skinChoices[s];                                                                    //Option chosen by user

//User Hair Colour
int h = 0;                          
color []hairChoices = {#ffe69e,#e6cea8,#b89778,#b55239,#6a4e42,#2c222b,#ffc0cf,#c0faf5,#cec9e5,#71c7e5,#c2eace};
color hairColour = hairChoices[h];                                                             

//User Hair Style
int hs = 0;                            
int []hairStyles = {1,2,3,4,5,6};
int hairStyle = hairStyles[hs];                              

//User Eye Colour
int e = 0;                            
color []eyeChoices = {#451800,#63390f,#19a337,#699bcb,#d0e8ff,#ffc100,#b0df00,#009bd6,#c30ba9,#d60e40};
color eyeColour = eyeChoices[e];                                                           

//User Shirt Colour
int shi = 0;
color []shirtChoices = {#ffffff,#958a7f,#fdeb71,#20419a,#8aadc5,
                        #ff7700,#00b064,#ff0000,#fdaebd,#5e3a8c};
color shirtColour = shirtChoices[shi];                                                

//User Pant Colour
int p = 0;                          
color []pantsChoices = {#ffffff,#958a7f,#fdeb71,#20419a,#8aadc5,
                        #ff7700,#00b064,#ff0000,#fdaebd,#5e3a8c};
color pantsColour = pantsChoices[p];                                                          

//User Belt Colour
int b = 0;                          
color []beltChoices = {#ffffff,#958a7f,#fdeb71,#20419a,#8aadc5,#60371f,
                        #ff7700,#00b064,#ff0000,#fdaebd,#5e3a8c};
color beltColour = beltChoices[b];                                                 

//User Shoe Colour
int sho = 0;                         
color []shoeChoices = {#ffffff,#958a7f,#fdeb71,#20419a,#8aadc5,
                        #ff7700,#00b064,#ff0000,#fdaebd,#5e3a8c};
color shoeColour = shoeChoices[sho];             


//Homescreen Variables:


boolean gamesave1 = false;                        //Helps display "Game saved"
boolean fullGallery = false;                      //Helps display "Gallery full"


//Paint Variables:


String title = "";
boolean noTitle = false;                          //Prevents user from having no art titles

//Paint Brush Colour
color paintColour = #000000;

//Palettes displayed on Paint screen
color [] palette1 = {#ff0000,#ffff00,#00ff00,#0000ff,#ffffff};

color [] palette2 = {#000000,#000000,#000000,#000000,#000000};

color [] palette3 = {#000000,#000000,#000000,#000000,#000000};

color [] palette4 = {#000000,#000000,#000000,#000000,#000000};

//Painting Background Colour
color backColour = #000000;

//User-Chosen Colour
int chosenR = 0;
int chosenG = 0;
int chosenB = 0;

int paintSize = 30;

//Used to determine how long user is painting for
double startTime;
double endTime;
double time;

//Used to record # of colours, paintbrush sizes, and stickers user uses
int colourChange = -1;
int sizeChange = 1;
int stickerChange = 0;

//Used to display stickers on Paint screen
boolean sticker1 = false;
boolean sticker2 = false;


//Gallery Variables:


String [] artTitle = {"","",""};          //Art titles
double [] artValue = new double [3];      //Art values

boolean [] paintings = {false,false,false};              //false = no painting displayed, true = painting displayed


//Shop Variables:


boolean notEnough = false;

color [][] palettes = {{#ff0000,#ffff00,#00ff00,#0000ff,#ffffff},  //starter colours
                       {#ffc0cb,#ffa500,#11beee,#800080,#521515},  //more starter colours
                       {#00cccc,#f08080,#fff68f,#d7b8ff,#00e595},  //summer colours
                       {#1e5636,#578c49,#99be8f,#8f8350,#b6a576},  //forest colours
                       {#8d5524,#c68642,#e0ac69,#f1c27d,#ffdbac},  //skintones
                       {#6b3e26,#ffc5d9,#c2f2d0,#fdf5c9,#ffcb85},  //ice cream colours
                      };

String [] sticker = {"star.png","heart.png","smileyface.png","chrisevans.png"};        //Sticker options (file names)
String [] ownedSticker = {"","","",""};                                                //Owned stickers
String [] selectedSticker = {"",""};                                                   //Selected stickers

boolean [] owned = {true,false,false,false,false,false,                //Palettes
                    false,false,false,false};                          //Stickers
                    
boolean [] selected = {true,false,false,false,false,false,             //Palettes
                        false,false,false,false};                      //Stickers
                        
boolean [] upgrades = {false,                     //Paint sizes
                       false};                     //Colour mixer


//Other:


boolean namer = false;                                                         //Allows user to type only when typing box is selected

color [] paletteEmpty = {#000000,#000000,#000000,#000000,#000000};             //Used to check if a palette is being used/not being used

DecimalFormat dFormatter = new DecimalFormat ("0.00");                         //Used to format/round user's money

double sTime;                                                                  //Helps display "Game saved" for a limited time

double fTime;                                                                  //Helps display "Gallery full" for a limited time

double mTime;                                                                  //Helps display "Not enough money" for a limited time





void setup () {
  
    f = loadFont("Super45.vlw");
    
    img = loadImage("paint.jpg");        //titlescreen background
    
    /* Sound files: */
    file = new SoundFile (this,"click.wav");
    fileChris = new SoundFile (this,"fondue.wav");
     
    background(0);
      
    strokeWeight(10);
      
    
    fullScreen();    //screen size: 1440 by 900
    
    smooth();
    
    frameRate(100000);     //high so painting is "smoother"
  
}




void mouseReleased () {
  
  
    if (show == "titlescreen") {
     
     
//"New Game" Button

        if (mouseX >= 480 && mouseX <= 480+480 && mouseY >= 300 && mouseY <= 300+200) {
          
            background (0);
            
            show = "characterSelect";
          
        }
        
        
//Continue Button

        if (mouseX >= 480 && mouseX <= 480+480 && mouseY >= 600 && mouseY <= 600+200) {
         
            String [] userInfo = loadStrings("GameSave1.txt");
          
            username = userInfo[0];
             
            //Character
            skinColour = color(int(userInfo[1]));
            hairColour = color(int(userInfo[2]));
            hairStyle = int(userInfo[3]);
            eyeColour = color(int(userInfo[4]));
            shirtColour = color(int(userInfo[5]));
            pantsColour = color(int(userInfo[6]));
            beltColour = color(int(userInfo[7]));
            shoeColour = color(int(userInfo[8]));
             
            //Paintings in Gallery
            artTitle[0] = userInfo[9];
            artTitle[1] = userInfo[10];
            artTitle[2] = userInfo[11];
             
            artValue[0] = Double.parseDouble(userInfo[12]);
            artValue[1] = Double.parseDouble(userInfo[13]);
            artValue[2] = Double.parseDouble(userInfo[14]);
             
            paintings[0] = boolean(userInfo[15]);
            paintings[1] = boolean(userInfo[16]);
            paintings[2] = boolean(userInfo[17]);
             
            //Money
            money = Double.parseDouble(userInfo[18]);
             
            //Items/Upgrades that are owned/have been selected
            owned[0] = boolean(userInfo[19]);
            owned[1] = boolean(userInfo[20]);
            owned[2] = boolean(userInfo[21]);
            owned[3] = boolean(userInfo[22]);
            owned[4] = boolean(userInfo[23]);
            owned[5] = boolean(userInfo[24]);
            owned[6] = boolean(userInfo[25]);
            owned[7] = boolean(userInfo[26]);
            owned[8] = boolean(userInfo[27]);
            owned[9] = boolean(userInfo[28]);
             
            selected[0] = boolean(userInfo[29]);
            selected[1] = boolean(userInfo[30]);
            selected[2] = boolean(userInfo[31]);
            selected[3] = boolean(userInfo[32]);
            selected[4] = boolean(userInfo[33]);
            selected[5] = boolean(userInfo[34]);
            selected[6] = boolean(userInfo[35]);
            selected[7] = boolean(userInfo[36]);
            selected[8] = boolean(userInfo[37]);
            selected[9] = boolean(userInfo[38]);
             
            palette1[0] = color(int(userInfo[39]));
            palette1[1] = color(int(userInfo[40]));
            palette1[2] = color(int(userInfo[41]));
            palette1[3] = color(int(userInfo[42]));
            palette1[4] = color(int(userInfo[43]));
             
            palette2[0] = color(int(userInfo[44]));
            palette2[1] = color(int(userInfo[45]));
            palette2[2] = color(int(userInfo[46]));
            palette2[3] = color(int(userInfo[47]));
            palette2[4] = color(int(userInfo[48]));
             
            palette3[0] = color(int(userInfo[49]));
            palette3[1] = color(int(userInfo[50]));
            palette3[2] = color(int(userInfo[51]));
            palette3[3] = color(int(userInfo[52]));
            palette3[4] = color(int(userInfo[53]));
             
            palette4[0] = color(int(userInfo[54]));
            palette4[1] = color(int(userInfo[55]));
            palette4[2] = color(int(userInfo[56]));
            palette4[3] = color(int(userInfo[57]));
            palette4[4] = color(int(userInfo[58]));
             
            upgrades[0] = boolean(userInfo[59]);
            upgrades[1] = boolean(userInfo[60]);
             
            ownedSticker[0] = userInfo[61];
            ownedSticker[1] = userInfo[62];
            ownedSticker[2] = userInfo[63];
            ownedSticker[3] = userInfo[64];
             
            selectedSticker[0] = userInfo[65];
            selectedSticker[1] = userInfo[66];
            
            sticker1 = boolean(userInfo[67]);
            sticker2 = boolean(userInfo[68]);

             
            show = "homescreen";
         
        }
        
    }
      
      
      
      
    if (show == "characterSelect") {
      
      
//Hair Colour Buttons

        if (h > 0 && mouseX > 1130 && mouseX < 1130+15 && mouseY > 28 && mouseY < 28+30) {
          
            file.play();
         
            h--;
            hairColour = hairChoices[h];
          
        }
        
        if (h < hairChoices.length-1 && mouseX > 1295 && mouseX < 1295+15 && mouseY > 28 && mouseY < 28+30) {
             
            file.play();
          
            h++;
            hairColour = hairChoices[h];
          
        }
        
        
//Hair Style Buttons

        if (hs > 0 && mouseX > 1130 && mouseX < 1130+15 && mouseY > 114 && mouseY < 114+30) {
         
            file.play();
          
            hs--;
            hairStyle = hairStyles[hs];
          
        }
        
        if (hs < hairStyles.length-1 && mouseX > 1295 && mouseX < 1295+15 && mouseY > 114 && mouseY < 114+30) {
         
            file.play();
          
            hs++;
            hairStyle = hairStyles[hs];
          
        }
        
        
//Skin Colour Buttons 
     
        if (s > 0 && mouseX > 1130 && mouseX < 1130+15 && mouseY > 200 && mouseY < 200+30) {
         
            file.play();
          
            s--;
            skinColour = skinChoices[s];
          
        }
        
        if (s < skinChoices.length-1 && mouseX > 1295 && mouseX < 1295+15 && mouseY > 200 && mouseY < 200+30) {
         
            file.play();
          
            s++;
            skinColour = skinChoices[s];
          
        }
        
    
//Eye Colour Buttons 

        if (e > 0 && mouseX > 1130 && mouseX < 1130+15 && mouseY > 286 && mouseY < 286+30) {
         
            file.play();
          
            e--;
            eyeColour = eyeChoices[e];
          
        }
        
        if (e < eyeChoices.length-1 && mouseX > 1295 && mouseX < 1295+15 && mouseY > 286 && mouseY < 286+30) {
         
            file.play();
          
            e++;
            eyeColour = eyeChoices[e];
          
        }
        
        
//Shirt Colour Buttons 
     
        if (shi > 0 && mouseX > 1130 && mouseX < 1130+15 && mouseY > 372 && mouseY < 372+30) {
         
            file.play();
          
            shi--;
            shirtColour = shirtChoices[shi];
          
        }
        
        if (shi < shirtChoices.length-1 && mouseX > 1295 && mouseX < 1295+15 && mouseY > 372 && mouseY < 372+30) {
         
            file.play();
          
            shi++;
            shirtColour = shirtChoices[shi];
          
        }
        
        
//Pant Colour Buttons 

        if (p > 0 && mouseX > 1130 && mouseX < 1130+15 && mouseY > 458 && mouseY < 458+30) {
         
            file.play();
          
            p--;
            pantsColour = pantsChoices[p];
          
        }
        
        if (p < pantsChoices.length-1 && mouseX > 1295 && mouseX < 1295+15 && mouseY > 458 && mouseY < 458+30) {
         
            file.play();
          
            p++;
            pantsColour = pantsChoices[p];
          
        }
        
        
//Belt Colour Buttons 

        if (b > 0 && mouseX > 1130 && mouseX < 1130+15 && mouseY > 544 && mouseY < 544+30) {
         
            file.play();
          
            b--;
            beltColour = beltChoices[b];
          
        }
        
        if (b < beltChoices.length-1 && mouseX > 1295 && mouseX < 1295+15 && mouseY > 544 && mouseY < 544+30) {
         
            file.play();
          
            b++;
            beltColour = beltChoices[b];
          
        }
        
        
//Shoe Colour Buttons

        if (sho > 0 && mouseX > 1130 && mouseX < 1130+15 && mouseY > 630 && mouseY < 630+30) {
         
            file.play();
          
            sho--;
            shoeColour = shoeChoices[sho];
          
          
        }
        
        if (sho < shoeChoices.length-1 && mouseX > 1295 && mouseX < 1295+15 && mouseY > 630 && mouseY < 630+30) {
         
            file.play();
          
            sho++;
            shoeColour = shoeChoices[sho];
          
        }
        
        
//Done Button 

        if (mouseX > 1300 && mouseX < 1300+100 && mouseY > 810 && mouseY < 810+50 && !(username.equals(""))) {
         
            show = "homescreen";
            namer = false;
          
        }
        
        if (mouseX > 1300 && mouseX < 1300+100 && mouseY > 810 && mouseY < 810+50 && username.equals("")) {
         
            noUsername = true;
          
        }
        
        
//Username Box

        if (mouseX > 395 && mouseX < 395+556 && mouseY > 750 && mouseY < 750+100) {
           
            namer = true;
          
        }
        
        if (!(mouseX > 395 && mouseX < 395+556 && mouseY > 750 && mouseY < 750+100)) {
    
            namer = false;
          
        }
      
    }
    
    
    
    
    if (show == "homescreen") {
      
      
//Paint Button
   
        if (mouseX > 200 && mouseX < 200+300 && mouseY > 200 && mouseY < 200+150) {
          
            if (paintings[0] && paintings[1] && paintings[2]) {         //if gallery is full
              
                fullGallery = true;
                fTime = millis();
              
            }
            
            else {
          
                background(0);
                
                noTitle = false;
                
                startTime = millis();
                
                show = "paint";
            
            }
          
        }
        
        
//Gallery Button
   
        if (mouseX > 200 && mouseX < 200+300 && mouseY > 500 && mouseY < 500+150) {
          
            background(0);
            
            show = "gallery";
          
        }
        
        
//Art Shop Button

        if (mouseX > 940 && mouseX < 940+300 && mouseY > 200 && mouseY < 200+150) {
          
            background(0);
            
            show = "shop1";
          
        }
        
        
//Art Competition Button

        if (mouseX > 940 && mouseX < 940+300 && mouseY > 500 && mouseY < 500+150) {
          
            background(0);
            
            show = "comp";
          
        }
        
        
//Save Game Button

        if (mouseX > 1280 && mouseX < 1280+110 && mouseY > 50 && mouseY < 50+50) {

            String [] userSave = {username,
            
                                  //Character
                                  str(skinColour),
                                  str(hairColour),
                                  str(hairStyle),
                                  str(eyeColour),
                                  str(shirtColour),
                                  str(pantsColour),
                                  str(beltColour),
                                  str(shoeColour),
                                  
                                  //Paintings in Gallery
                                  artTitle[0],
                                  artTitle[1],
                                  artTitle[2],
                                  
                                  Double.toString(artValue[0]),
                                  Double.toString(artValue[1]),
                                  Double.toString(artValue[2]),
                                  
                                  str(paintings[0]),
                                  str(paintings[1]),
                                  str(paintings[2]),
                                  
                                  //Money
                                  Double.toString(money),
                                  
                                  //Items/upgrades that are owned/have been selected
                                  str(owned[0]),
                                  str(owned[1]),
                                  str(owned[2]),
                                  str(owned[3]),
                                  str(owned[4]),
                                  str(owned[5]),
                                  str(owned[6]),
                                  str(owned[7]),
                                  str(owned[8]),
                                  str(owned[9]),
                                  
                                  str(selected[0]),
                                  str(selected[1]),
                                  str(selected[2]),
                                  str(selected[3]),
                                  str(selected[4]),
                                  str(selected[5]),
                                  str(selected[6]),
                                  str(selected[7]),
                                  str(selected[8]),
                                  str(selected[9]),
                                  
                                  str(palette1[0]),
                                  str(palette1[1]),
                                  str(palette1[2]),
                                  str(palette1[3]),
                                  str(palette1[4]),
                                  
                                  str(palette2[0]),
                                  str(palette2[1]),
                                  str(palette2[2]),
                                  str(palette2[3]),
                                  str(palette2[4]),
                                  
                                  str(palette3[0]),
                                  str(palette3[1]),
                                  str(palette3[2]),
                                  str(palette3[3]),
                                  str(palette3[4]),
                                  
                                  str(palette4[0]),
                                  str(palette4[1]),
                                  str(palette4[2]),
                                  str(palette4[3]),
                                  str(palette4[4]),
                                  
                                  str(upgrades[0]),
                                  str(upgrades[1]),
                                  
                                  ownedSticker[0],
                                  ownedSticker[1],
                                  ownedSticker[2],
                                  ownedSticker[3],
                                  
                                  selectedSticker[0],
                                  selectedSticker[1],
                                  
                                  str(sticker1),
                                  str(sticker2)
                                  };
           
            saveStrings("GameSave1.txt",userSave);          //Saved onto GameSave1.txt file
            
            gamesave1 = true;
            sTime = millis();
        
        }
      
    }




    if (show == "paint") {
      
      
//Determining colourChange, sizeChange and stickerChange:

        if ((mouseX > 25 && mouseX < 25+250 && mouseY > 25 && mouseY < 25+50 && (palette1[0] != paletteEmpty[0]))  ||                 //palette 1
            (mouseX > 25 && mouseX < 25+250 && mouseY > 100 && mouseY < 100+50 && (palette2[0] != paletteEmpty[0])) ||                 //palette 2
            (mouseX > 25 && mouseX < 25+250 && mouseY > 175 && mouseY < 175+50 && (palette3[0] != paletteEmpty[0])) ||                 //palette 3
            (mouseX > 25 && mouseX < 25+250 && mouseY > 250 && mouseY < 250+50 && (palette4[0] != paletteEmpty[0]))) {                //palette 4
         
            colourChange++;
          
        }
        
        if (upgrades[0] && ((mouseX > 135 && mouseX < 135+10 && mouseY > 525 && mouseY < 525+10) ||                 //small paint
            (mouseX > 165 && mouseX < 165+30 && mouseY > 515 && mouseY < 515+30) ||                                //medium paint
            (mouseX > 215 && mouseX < 215+60 && mouseY > 500 && mouseY < 500+60))) {                                //large paint
         
            sizeChange++;
          
        } 
        
        if ((mouseX > 30 && mouseX < 30+105 && mouseY > 350 && mouseY < 350+105 && (selectedSticker[0] != ""))  ||             //Sticker 1
            (mouseX > 165 && mouseX < 165+105 && mouseY > 350 && mouseY < 350+105 && (selectedSticker[1] != ""))){             //Sticker 2
         
            stickerChange++;
          
        }
        
        
//Selected sticker
        
        if (mouseX > 30 && mouseX < 30+105 && mouseY > 350 && mouseY < 350+105 && !(sticker1) && !(selectedSticker[0].equals(""))) {
              
             sticker1 = true;
             
          
        }
        
        if (mouseX > 165 && mouseX < 165+105 && mouseY > 350 && mouseY < 350+105 && !(sticker2) && !(selectedSticker[1].equals(""))) {

             sticker2 = true;
            
          
        }
        
        if (!(mouseX > 30 && mouseX < 30+105 && mouseY > 350 && mouseY < 350+105) && !(mouseX > 300 && mouseY < 700) && sticker1) {
          
              sticker1 = false;
          
        }
        
        if (!(mouseX > 165 && mouseX < 165+105 && mouseY > 350 && mouseY < 350+105) && !(mouseX > 300 && mouseY < 700) && sticker2) {
          
              sticker2 = false;
          
        }
      
     
//Art Title Box

        if (mouseX > 395 && mouseX < 395+556 && mouseY > 750 && mouseY < 750+100) {
           
            namer = true;
          
        }
        
        if (!(mouseX > 395 && mouseX < 395+556 && mouseY > 750 && mouseY < 750+100)) {
    
            namer = false;
          
        }
        
        
//Save and Finish Art Button
        
        if (mouseX > 1250 && mouseX < 1250+150 && mouseY > 810 && mouseY < 810+50 && !(title.equals(""))) {
            
            endTime = millis();
            time = (endTime - startTime)/1000;
            
            if (!(paintings[0])){          //if 1st Gallery slot is open
              
                paintings[0] = true;
                artValue[0] = decideArtValue (time,colourChange,sizeChange,stickerChange);
                saveFrame("painting-0000.png");
                artTitle[0] = title;
                title = "";
                noTitle = false;
              
            }
            
            else if (!(paintings[1])){          //if 2nd Gallery slot is open
              
                paintings[1] = true;
                artValue[1] = decideArtValue (time,colourChange,sizeChange,stickerChange);
                saveFrame("painting-0001.png");
                artTitle[1] = title;
                title = "";
                noTitle = false;
              
            }
            
            else if (!(paintings[2])){          //if 3rd Gallery slot is open
              
                paintings[2] = true;
                artValue[2] = decideArtValue (time,colourChange,sizeChange,stickerChange);
                saveFrame("painting-0002.png");
                artTitle[2] = title;
                title = "";
                noTitle = false;
              
            }
            
      //Reset all paint variables
      
            colourChange = -1;
            sizeChange = 1;
            stickerChange = 0;
            sticker1 = false;
            sticker2 = false;
            paintSize = 30;
            paintColour = backColour;
            
            show = "homescreen";
          
        }


//If there is no art title

        if (mouseX > 1250 && mouseX < 1250+150 && mouseY > 810 && mouseY < 810+50 && title.equals("")) {
         
            noTitle = true;
          
        }
        
        
//Quit Button 

        if (mouseX > 1060 && mouseX < 1060+150 && mouseY > 810 && mouseY < 810+50){
            
      //Reset all paint variables
          
            colourChange = -1;
            sizeChange = 1;
            stickerChange = 0;
            sticker1 = false;
            sticker2 = false;
            paintColour = backColour;
            paintSize = 30;
            title = "";
            
            show = "homescreen";
          
        }
      
    }
    
    
    
    
    if (show == "gallery") {  
      
     
//Back to Homescreen Button
      
        if (mouseX > 1260 && mouseX < 1260+150 && mouseY > 805 && mouseY < 805+70) {
         
            show = "homescreen";
          
        }
        
        
//"Sell Painting" Button
        
        if (mouseX > 83 && mouseX < 83+150 && mouseY > 675 && mouseY < 675+75 && paintings[0]) {
         
            money = money + artValue[0];

            artValue[0] = 0;
            artTitle[0] = "";
            
            paintings[0] = false;
            
            background(0);
          
        }
        
        if (mouseX > 543 && mouseX < 543+150 && mouseY > 675 && mouseY < 675+75 && paintings[1]) {
         
            money = money + artValue[1];

            artValue[1] = 0;
            artTitle[1] = "";

            paintings[1] = false;
            
            background(0);
          
        }
        
        if (mouseX > 1003 && mouseX < 1003+150 && mouseY > 675 && mouseY < 675+75 && paintings[2]) {
         
            money = money + artValue[2];
            
            artValue[2] = 0;
            artTitle[2] = "";
            
            paintings[2] = false;
            
            background(0);
          
        }
        
        
//"Enter in Competition" Button
        
        if (mouseX > 286 && mouseX < 286+150 && mouseY > 675 && mouseY < 675+75 && paintings[0]) {
            
            background(0);
            show = "comp";
          
        }
        
        if (mouseX > 746 && mouseX < 746+150 && mouseY > 675 && mouseY < 675+75 && paintings[1]) {
         
            background(0);
            show = "comp";
          
        }
        
        if (mouseX > 1206 && mouseX < 1206+150 && mouseY > 675 && mouseY < 675+75 && paintings[2]) {
         
            background(0);
            show = "comp";
          
        }
      
    }
    
    

    
    if (show == "shop1") {
      

//"Return to Homescreen" Button

            if (mouseX > 1260 && mouseX < 1260+150 && mouseY > 805 && mouseY < 805+70) {
             
                show = "homescreen";
              
            }
            
            
//Palette 1     

            if (mouseX > 223 && mouseX < 223+117 && mouseY > 390 && mouseY < 390+50) {
              
                if (money < 100 && !(owned[0])) {        //If not enough money
                  
                    notEnough = true;
                    mTime = millis();
                  
                }
                
                else {
                  
                    chosen(1,100);
                
                }
              
            }
            
            
//Palette 2 

            if (mouseX > 223 && mouseX < 223+117 && mouseY > 530 && mouseY < 530+50) {
                
                if (money < 100 && !(owned[1])) {        //If not enough money
                  
                    notEnough = true;
                    mTime = millis();
                  
                }
                
                else {
                  
                    chosen(2,100);
                
                }
                
            }
            
            
//Palette 3  
        
            if (mouseX > 223 && mouseX < 223+117 && mouseY > 672 && mouseY < 672+50) {
                
                if (money < 500 && !(owned[2])) {        //If not enough money
                  
                    notEnough = true;
                    mTime = millis();
                  
                }
                
                else {
                  
                    chosen(3,500);
                
                }
              
            }
            
            
//Palette 4   
        
            if (mouseX > 593 && mouseX < 593+117 && mouseY > 390 && mouseY < 390+50) {
              
                if (money < 250 && !(owned[3])) {        //If not enough money
                  
                    notEnough = true;
                    mTime = millis();
                  
                }
                
                else {
                  
                    chosen(4,250);
                
                }       
              
            }
            
            
//Palette 5   
        
            if (mouseX > 593 && mouseX < 593+117 && mouseY > 530 && mouseY < 530+50) {
              
                if (money < 250 && !(owned[4])) {        //If not enough money
                  
                    notEnough = true;
                    mTime = millis();
                  
                }
                
                else {
                  
                    chosen(5,250);
                
                }
    
            }
            
            
//Palette 6
        
            if (mouseX > 593 && mouseX < 593+117 && mouseY > 672 && mouseY < 672+50) {
              
                if (money < 500 && !(owned[5])) {        //If not enough money
                  
                    notEnough = true;
                    mTime = millis();
                  
                }
                
                else {
                  
                    chosen(6,500);
                
                }
              
            }
        
        
//Paint Sizes  
    
        if (mouseX > 1110 && mouseX < 1110+150 && mouseY > 395 && mouseY < 395+50 && !(upgrades[0])) {
          
            if (money < 100) {            //If not enough money
              
                notEnough = true;
                mTime = millis();
              
            }
            
            else {
              
                money = money - 100;
                upgrades[0] = true;
            
            }
          
        }
        
        
//Colour Mixer
    
        if (mouseX > 1110 && mouseX < 1110+150 && mouseY > 690 && mouseY < 690+50 && !(upgrades[1])) {
            
            if (money < 1000){            //If not enough money
              
                notEnough = true;
                mTime = millis();
              
            }
            
            else {
              
                money = money - 1000;
                upgrades[1] = true;
            
            }
          
        }
        
        
//If right arrow button is pressed (next section of shop)
        
        if (mouseX > 865 && mouseX < 865+25 && mouseY > 682 && mouseY < 682+25) {
          
              show = "shop2";
          
        }
        
    }
        
        
        
        
    if (show == "shop2") {

    
//"Return to Homescreen" Button
     
        if (mouseX > 1260 && mouseX < 1260+150 && mouseY > 805 && mouseY < 805+70) {
         
            show = "homescreen";
          
        }
        
        
//Sticker 1 (star) 
    
        if (mouseX > 255 && mouseX < 255+100 && mouseY > 450 && mouseY < 450+50) {
          
            if (money < 50 && !(owned[6])) {        //If not enough money
              
                notEnough = true;
                mTime = millis();
              
            }
            
            else {
              
                chosenSticker(1,50);
            
            }
          
        }
        
        
//Sticker 2 (heart) 

        if (mouseX > 255 && mouseX < 255+100 && mouseY > 690 && mouseY < 690+50) {
            
            if (money < 50 && !(owned[7])) {        //If not enough money
              
                notEnough = true;
                mTime = millis();
              
            }
            
            else {
              
                chosenSticker(2,50);
            
            }
            
        }
        
        
//Sticker 3  (smiley face) 

        if (mouseX > 570 && mouseX < 570+100 && mouseY > 450 && mouseY < 450+50) {
            
            if (money < 150 && !(owned[8])) {        //If not enough money
              
                notEnough = true;
                mTime = millis();
              
            }
            
            else {
              
                chosenSticker(3,150);
            
            }
          
        }
        
        
//Sticker 4 (Chris Evans) 
    
        if (mouseX > 570 && mouseX < 570+100 && mouseY > 690 && mouseY < 690+50) {
          
            if (money < 10000 && !(owned[9])) {        //If not enough money
              
                notEnough = true;
                mTime = millis();
              
            }
            
            else {
              
                chosenSticker(4,10000);
            
            }       
          
        }
        
    
//Paint Sizes  

        if (mouseX > 1110 && mouseX < 1110+150 && mouseY > 395 && mouseY < 395+50 && !(upgrades[0])) {
          
            if (money < 100) {        //If not enough money
              
                notEnough = true;
                mTime = millis();
              
            }
            
            else {
              
                money = money - 100;
                upgrades[0] = true;
            
            }
          
        }
        
        
//Colour Mixer
    
        if (mouseX > 1110 && mouseX < 1110+150 && mouseY > 690 && mouseY < 690+50 && !(upgrades[1])) {
            
            if (money < 1000){        //If not enough money
              
                notEnough = true;
                mTime = millis();
              
            }
            
            else {
              
                money = money - 1000;
                upgrades[1] = true;
            
            }
          
        }
        
        
//If left arrow button is pressed (previous section of shop)
        
        if (mouseX > 780 && mouseX < 780+25 && mouseY > 682 && mouseY < 682+25) {
          
              show = "shop1";
          
        }

    }



    
    if (show == "comp"){
      
      
//"Back to Homescreen" Button
     
        if (mouseX > 1260 && mouseX < 1260+150 && mouseY > 805 && mouseY < 805+70) {
         
            show = "homescreen";
          
        }
        
    }
    
}




void keyPressed () {

  
    if (show == "characterSelect") {          //For choosing username
      
        if (namer) {
         
            if ((key == BACKSPACE) && username.length() > 0) {
             
                username = username.substring(0,username.length()-1);
              
            }
            
            else if ((username.length() < 15) && (key > 31) && (key != CODED)) {          //Excludes shift, caps lock, etc
              
                username = username + key;
              
            }
          
        }
      
    }
    
    
    
    
    if (show == "paint") {          //For choosing art title
      
        if (namer) {
         
            if ((key == BACKSPACE) && title.length() > 0) {
             
                title = title.substring(0,title.length()-1);
              
            }
            
            else if ((title.length() < 15) && (key > 31) && (key != CODED)) {          //Excludes shift, caps lock, etc
              
                title = title + key;
              
            }
          
        }
      
    }
  
} 




void drawCharacter (int x, int y, float size) {          //Draws character given the x and y coordinates and desired size
  
//Character:
    
    //Hair
    if (hairStyle == 1) {
      
        strokeWeight(3);
        stroke (hairColour);
        fill (hairColour);
        ellipse (x,y-(100*size),(60*size),(80*size));
        
        stroke (0);
        fill (0);
        rect (x-(40*size),y-(100*size),(80*size),(50*size));
      
    }
    
    if (hairStyle == 2) {
      
        strokeWeight(3);
        stroke (hairColour);
        fill (hairColour);
        ellipse (x,y-(95*size),(80*size),(100*size));
      
    }
    
    if (hairStyle == 3) {
      
        strokeWeight(3);
        stroke (hairColour);
        fill (hairColour);
        rect (x-(35*size),y-(140*size),(70*size),(70*size),60,60,75,75);
        strokeWeight(30);
        stroke(0);
      
    }
    
    if (hairStyle == 4) {
      
        strokeWeight(3);
        stroke (hairColour);
        fill (hairColour);
        ellipse (x,y-(125*size),(100*size),(100*size));
      
    }
    
    if (hairStyle == 5) {
      
        strokeWeight(3);
        stroke (hairColour);
        fill (hairColour);
        ellipse (x,y-(110*size),(80*size),(70*size));
        
        ellipse (x-(42*size),y-(120*size),(30*size),(30*size));
        ellipse (x+(42*size),y-(120*size),(30*size),(30*size));
      
    }
    
    if (hairStyle == 6) {
      
        strokeWeight(3);
        stroke (hairColour);
        fill (hairColour);
        rect (x-(35*size),y-(160*size),(70*size),(90*size),60,60,75,75);
        strokeWeight(30);
        stroke(0);
      
    }
    
    //Face
    strokeWeight(3);
    stroke (skinColour);
    fill (skinColour);
    ellipse (x,y-(100*size),(60*size),(60*size));
    
    //Left Eye
    stroke (eyeColour);
    fill (eyeColour);
    ellipse (x-(10*size),y-(100*size),(10*size),(5*size));
    
    //Right Eye
    stroke (eyeColour);
    fill (eyeColour);
    ellipse (x+(10*size),y-(100*size),(10*size),(5*size));
    
    //Neck
    stroke (skinColour);
    fill (skinColour);    
    rect (x-(10*size),y-(75*size),(20*size),(30*size));
    
    //Body
    stroke (skinColour);
    fill (skinColour);   
    ellipse (x,y,(50*size),(120*size));
    //rect (x-30,y-50,60,100);    
    
    //Left Arm
    strokeWeight(6);
    stroke (skinColour);  
    line (x-(20*size),y-(40*size),x-(50*size),y+(35*size)); 
    
    //Right Arm
    strokeWeight(6);
    stroke (skinColour);  
    line (x+(20*size),y-(40*size),x+(50*size),y+(35*size));  
    
    //Left Leg
    strokeWeight(3);
    stroke (skinColour);
    fill (skinColour);    
    rect (x-(15*size),y+(50*size),(5*size),(120*size));  
    
    //Right Leg
    stroke (skinColour);
    fill (skinColour);    
    rect (x+(10*size),y+(50*size),(5*size),(120*size));  
    
    //Shirt
    stroke (shirtColour);
    fill (shirtColour);    
    ellipse (x,y-(5*size),(52*size),(110*size));
    
    //Pants
    stroke (pantsColour);
    fill (pantsColour);    
    rect (x-(25*size),y+(15*size),(50*size),(50*size));
    rect (x-(25*size),y+(65*size),(20*size),(30*size));
    rect (x+(5*size),y+(65*size),(20*size),(30*size));
    
    //Belt
    stroke (beltColour);
    fill (beltColour);  
    rect (x-(25*size),y+(15*size),(50*size),(5*size));
    
    //Left Shoe
    stroke (shoeColour);
    fill (shoeColour);  
    rect (x-(25*size),y+(165*size),(15*size),(6*size));
    
    //Right Shoe
    stroke (shoeColour);
    fill (shoeColour);  
    rect (x+(10*size),y+(165*size),(15*size),(6*size));
  
}




double decideArtValue (double time, int colourChange, int sizeChange, int stickerChange){          //Decides art value using time taken drawing, # of colour changes, 
                                                                                                   //# of paintbrush size changes, and # of sticker changes
    double artValue;
    double artValueF;
    double a;
    
    if (colourChange == -1 && sizeChange == 1 && stickerChange == 0) {
      
        a = -1;
      
    }
    
    else if (time <= 60) {          //1 minute
      
        a = random(0,5);
      
    }
    
    else if (time <= 2*60) {          //2 minutes
      
        a = random(2,10);
      
    }
    
    else if (time <= 3*60) {          //3 minutes
      
        a = random(3,15);
      
    }
    
    else if (time <= 4*60) {          //4 minutes
      
        a = random(4,20);
      
    }
    
    else if (time <= 5*60) {          //5 minutes
      
        a = random(5,25);
      
    }
    
    else if (time <= 10*60) {          //10 minutes
      
        a = random(5,30);
      
    }
    
    else if (time <= 20*60) {          //20 minutes
      
        a = random(15,40);
      
    }
    
    else if (time <= 30*60) {          //30 minutes
      
        a = random(25,50);
      
    }
    
    else if (time <= 60*60) {          //1 hour
      
        a = random(35,60);
      
    }
    
    else {                            //more than 1 hour
     
        a = random(50,100);
      
    }
    
    
    artValue = a + (Math.abs(colourChange*sizeChange)) + (stickerChange*0.2);
    
    String formatted = dFormatter.format(artValue);
    
    artValueF = Double.parseDouble(formatted);
    
    return artValueF;
  
}




void chosen (int paletteNum, double price) {              //Determines what palettes are owned by user/selected on the paint screen
  
    paletteNum--;
  
    if (!(owned[paletteNum])) {                                      //if palette is not owned by user
     
        money = money - price;
        owned[paletteNum] = true;
      
    }
    
    else if (!(selected[paletteNum]) && owned[paletteNum]){          //if palette is not selected by user
      
        if (palette1[0] == paletteEmpty[0]) {
          
            palette1[0] = palettes[paletteNum][0];
            palette1[1] = palettes[paletteNum][1];
            palette1[2] = palettes[paletteNum][2];
            palette1[3] = palettes[paletteNum][3];
            palette1[4] = palettes[paletteNum][4];
            selected[paletteNum] = true;
          
        }
        
        else if (palette2[0] == paletteEmpty[0]) {
          
            palette2[0] = palettes[paletteNum][0];
            palette2[1] = palettes[paletteNum][1];
            palette2[2] = palettes[paletteNum][2];
            palette2[3] = palettes[paletteNum][3];
            palette2[4] = palettes[paletteNum][4];
            selected[paletteNum] = true;
          
        }
        
        else if (palette3[0] == paletteEmpty[0]) {
          
            palette3[0] = palettes[paletteNum][0];
            palette3[1] = palettes[paletteNum][1];
            palette3[2] = palettes[paletteNum][2];
            palette3[3] = palettes[paletteNum][3];
            palette3[4] = palettes[paletteNum][4];
            selected[paletteNum] = true;
          
        }
        
        else if (palette4[0] == paletteEmpty[0]) {
          
            palette4[0] = palettes[paletteNum][0];
            palette4[1] = palettes[paletteNum][1];
            palette4[2] = palettes[paletteNum][2];
            palette4[3] = palettes[paletteNum][3];
            palette4[4] = palettes[paletteNum][4];
            selected[paletteNum] = true;
          
        }
      
    }
    
    else if (selected[paletteNum] && owned[paletteNum]) {          //if palette is selected by user
        
        if (palettes[paletteNum][0] == palette1[0]) {
          
            palette1[0] = paletteEmpty[0];
            palette1[1] = paletteEmpty[1];
            palette1[2] = paletteEmpty[2];
            palette1[3] = paletteEmpty[3];
            palette1[4] = paletteEmpty[4];
          
        }
        
        else if (palettes[paletteNum][0] == palette2[0]) {
          
            palette2[0] = paletteEmpty[0];
            palette2[1] = paletteEmpty[1];
            palette2[2] = paletteEmpty[2];
            palette2[3] = paletteEmpty[3];
            palette2[4] = paletteEmpty[4];
          
        }
        
        else if (palettes[paletteNum][0] == palette3[0]) {
          
            palette3[0] = paletteEmpty[0];
            palette3[1] = paletteEmpty[1];
            palette3[2] = paletteEmpty[2];
            palette3[3] = paletteEmpty[3];
            palette3[4] = paletteEmpty[4];
          
        }
        
        else if (palettes[paletteNum][0] == palette4[0]) {
          
            palette4[0] = paletteEmpty[0];
            palette4[1] = paletteEmpty[1];
            palette4[2] = paletteEmpty[2];
            palette4[3] = paletteEmpty[3];
            palette4[4] = paletteEmpty[4];
          
        }
        
        selected[paletteNum] = false;
      
    }
  
}




void chosenSticker (int stickerNum, double price) {              //Determines what stickers are owned by user/selected on the paint screen
  
    stickerNum--;
  
    if (!(owned[stickerNum+6])) {                                    //if palette is not owned by user
     
        money = money - price;
        owned[stickerNum+6] = true;
        
        if (ownedSticker[0].equals("")) {
          
            ownedSticker[0] = sticker[stickerNum];
          
        }
        
        else if (ownedSticker[1].equals("")) {
          
            ownedSticker[1] = sticker[stickerNum];
          
        }
        
        else if (ownedSticker[2].equals("")) {
          
            ownedSticker[2] = sticker[stickerNum];
          
        }
        
        else if (ownedSticker[3].equals("")) {
          
            ownedSticker[3] = sticker[stickerNum];
          
        }
      
    }
    
    else if (!(selected[stickerNum+6]) && owned[stickerNum+6]){          //if palette is not selected by user
      
        if (selectedSticker[0].equals("")) {
          
            selectedSticker[0] = sticker[stickerNum];
            selected[stickerNum+6] = true;

        }
        
        else if (selectedSticker[1].equals("")) {
          
            selectedSticker[1] = sticker[stickerNum];
            selected[stickerNum+6] = true;

        }
      
    }
    
    else if (selected[stickerNum+6] && owned[stickerNum+6]) {          //if palette is selected by user
        
        if (sticker[stickerNum].equals(selectedSticker[0])) {
          
            selectedSticker[0] = "";
            sticker1= false;
          
        }
        
        else if (sticker[stickerNum].equals(selectedSticker[1])) {
          
            selectedSticker[1] = "";
            sticker2 = false;
          
        }
        
        selected[stickerNum+6] = false;
      
    }

}




void mouseMoved () {
 
  println (mouseX + " " + mouseY + " " + show + " " + username);
  
}
