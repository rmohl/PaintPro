import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.text.DecimalFormat; 
import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class PaintPro extends PApplet {

/*
 * Program title:                Paint Pro
 * Author:                       Rachael Mohl
 * Last Updated (Version 1.0):   Sunday, June 17, 2018
 * Last Updated (Version 2.0):   Wednesday, April 29, 2020
 * Instructor/course:            Wei Qin/ICS3U
 */


/* Imports: */





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
int []skinChoices = {0xffffdbac,0xfff1c27d,0xffe0ac69,0xffc68642,0xff8d5524,0xffef6a65,0xffed3d51,0xff93d2c7,0xff331748};      //User options
int skinColour = skinChoices[s];                                                                    //Option chosen by user

//User Hair Colour
int h = 0;                          
int []hairChoices = {0xffffe69e,0xffe6cea8,0xffb89778,0xffb55239,0xff6a4e42,0xff2c222b,0xffffc0cf,0xffc0faf5,0xffcec9e5,0xff71c7e5,0xffc2eace};
int hairColour = hairChoices[h];                                                             

//User Hair Style
int hs = 0;                            
int []hairStyles = {1,2,3,4,5,6};
int hairStyle = hairStyles[hs];                              

//User Eye Colour
int e = 0;                            
int []eyeChoices = {0xff451800,0xff63390f,0xff19a337,0xff699bcb,0xffd0e8ff,0xffffc100,0xffb0df00,0xff009bd6,0xffc30ba9,0xffd60e40};
int eyeColour = eyeChoices[e];                                                           

//User Shirt Colour
int shi = 0;
int []shirtChoices = {0xffffffff,0xff958a7f,0xfffdeb71,0xff20419a,0xff8aadc5,
                        0xffff7700,0xff00b064,0xffff0000,0xfffdaebd,0xff5e3a8c};
int shirtColour = shirtChoices[shi];                                                

//User Pant Colour
int p = 0;                          
int []pantsChoices = {0xffffffff,0xff958a7f,0xfffdeb71,0xff20419a,0xff8aadc5,
                        0xffff7700,0xff00b064,0xffff0000,0xfffdaebd,0xff5e3a8c};
int pantsColour = pantsChoices[p];                                                          

//User Belt Colour
int b = 0;                          
int []beltChoices = {0xffffffff,0xff958a7f,0xfffdeb71,0xff20419a,0xff8aadc5,0xff60371f,
                        0xffff7700,0xff00b064,0xffff0000,0xfffdaebd,0xff5e3a8c};
int beltColour = beltChoices[b];                                                 

//User Shoe Colour
int sho = 0;                         
int []shoeChoices = {0xffffffff,0xff958a7f,0xfffdeb71,0xff20419a,0xff8aadc5,
                        0xffff7700,0xff00b064,0xffff0000,0xfffdaebd,0xff5e3a8c};
int shoeColour = shoeChoices[sho];             


//Homescreen Variables:


boolean gamesave1 = false;                        //Helps display "Game saved"
boolean fullGallery = false;                      //Helps display "Gallery full"


//Paint Variables:


String title = "";
boolean noTitle = false;                          //Prevents user from having no art titles

//Paint Brush Colour
int paintColour = 0xff000000;

//Palettes displayed on Paint screen
int [] palette1 = {0xffff0000,0xffffff00,0xff00ff00,0xff0000ff,0xffffffff};

int [] palette2 = {0xff000000,0xff000000,0xff000000,0xff000000,0xff000000};

int [] palette3 = {0xff000000,0xff000000,0xff000000,0xff000000,0xff000000};

int [] palette4 = {0xff000000,0xff000000,0xff000000,0xff000000,0xff000000};

//Painting Background Colour
int backColour = 0xff000000;

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

int [][] palettes = {{0xffff0000,0xffffff00,0xff00ff00,0xff0000ff,0xffffffff},  //starter colours
                       {0xffffc0cb,0xffffa500,0xff11beee,0xff800080,0xff521515},  //more starter colours
                       {0xff00cccc,0xfff08080,0xfffff68f,0xffd7b8ff,0xff00e595},  //summer colours
                       {0xff1e5636,0xff578c49,0xff99be8f,0xff8f8350,0xffb6a576},  //forest colours
                       {0xff8d5524,0xffc68642,0xffe0ac69,0xfff1c27d,0xffffdbac},  //skintones
                       {0xff6b3e26,0xffffc5d9,0xffc2f2d0,0xfffdf5c9,0xffffcb85},  //ice cream colours
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

int [] paletteEmpty = {0xff000000,0xff000000,0xff000000,0xff000000,0xff000000};             //Used to check if a palette is being used/not being used

DecimalFormat dFormatter = new DecimalFormat ("0.00");                         //Used to format/round user's money

double sTime;                                                                  //Helps display "Game saved" for a limited time

double fTime;                                                                  //Helps display "Gallery full" for a limited time

double mTime;                                                                  //Helps display "Not enough money" for a limited time





public void setup () {
  
    f = loadFont("Super45.vlw");
    
    img = loadImage("paint.jpg");        //titlescreen background
    
    /* Sound files: */
    file = new SoundFile (this,"click.wav");
    fileChris = new SoundFile (this,"fondue.wav");
     
    background(0);
      
    strokeWeight(10);
      
    
        //screen size: 1440 by 900
    
    
    
    frameRate(100000);     //high so painting is "smoother"
  
}




public void mouseReleased () {
  
  
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
            skinColour = color(PApplet.parseInt(userInfo[1]));
            hairColour = color(PApplet.parseInt(userInfo[2]));
            hairStyle = PApplet.parseInt(userInfo[3]);
            eyeColour = color(PApplet.parseInt(userInfo[4]));
            shirtColour = color(PApplet.parseInt(userInfo[5]));
            pantsColour = color(PApplet.parseInt(userInfo[6]));
            beltColour = color(PApplet.parseInt(userInfo[7]));
            shoeColour = color(PApplet.parseInt(userInfo[8]));
             
            //Paintings in Gallery
            artTitle[0] = userInfo[9];
            artTitle[1] = userInfo[10];
            artTitle[2] = userInfo[11];
             
            artValue[0] = Double.parseDouble(userInfo[12]);
            artValue[1] = Double.parseDouble(userInfo[13]);
            artValue[2] = Double.parseDouble(userInfo[14]);
             
            paintings[0] = PApplet.parseBoolean(userInfo[15]);
            paintings[1] = PApplet.parseBoolean(userInfo[16]);
            paintings[2] = PApplet.parseBoolean(userInfo[17]);
             
            //Money
            money = Double.parseDouble(userInfo[18]);
             
            //Items/Upgrades that are owned/have been selected
            owned[0] = PApplet.parseBoolean(userInfo[19]);
            owned[1] = PApplet.parseBoolean(userInfo[20]);
            owned[2] = PApplet.parseBoolean(userInfo[21]);
            owned[3] = PApplet.parseBoolean(userInfo[22]);
            owned[4] = PApplet.parseBoolean(userInfo[23]);
            owned[5] = PApplet.parseBoolean(userInfo[24]);
            owned[6] = PApplet.parseBoolean(userInfo[25]);
            owned[7] = PApplet.parseBoolean(userInfo[26]);
            owned[8] = PApplet.parseBoolean(userInfo[27]);
            owned[9] = PApplet.parseBoolean(userInfo[28]);
             
            selected[0] = PApplet.parseBoolean(userInfo[29]);
            selected[1] = PApplet.parseBoolean(userInfo[30]);
            selected[2] = PApplet.parseBoolean(userInfo[31]);
            selected[3] = PApplet.parseBoolean(userInfo[32]);
            selected[4] = PApplet.parseBoolean(userInfo[33]);
            selected[5] = PApplet.parseBoolean(userInfo[34]);
            selected[6] = PApplet.parseBoolean(userInfo[35]);
            selected[7] = PApplet.parseBoolean(userInfo[36]);
            selected[8] = PApplet.parseBoolean(userInfo[37]);
            selected[9] = PApplet.parseBoolean(userInfo[38]);
             
            palette1[0] = color(PApplet.parseInt(userInfo[39]));
            palette1[1] = color(PApplet.parseInt(userInfo[40]));
            palette1[2] = color(PApplet.parseInt(userInfo[41]));
            palette1[3] = color(PApplet.parseInt(userInfo[42]));
            palette1[4] = color(PApplet.parseInt(userInfo[43]));
             
            palette2[0] = color(PApplet.parseInt(userInfo[44]));
            palette2[1] = color(PApplet.parseInt(userInfo[45]));
            palette2[2] = color(PApplet.parseInt(userInfo[46]));
            palette2[3] = color(PApplet.parseInt(userInfo[47]));
            palette2[4] = color(PApplet.parseInt(userInfo[48]));
             
            palette3[0] = color(PApplet.parseInt(userInfo[49]));
            palette3[1] = color(PApplet.parseInt(userInfo[50]));
            palette3[2] = color(PApplet.parseInt(userInfo[51]));
            palette3[3] = color(PApplet.parseInt(userInfo[52]));
            palette3[4] = color(PApplet.parseInt(userInfo[53]));
             
            palette4[0] = color(PApplet.parseInt(userInfo[54]));
            palette4[1] = color(PApplet.parseInt(userInfo[55]));
            palette4[2] = color(PApplet.parseInt(userInfo[56]));
            palette4[3] = color(PApplet.parseInt(userInfo[57]));
            palette4[4] = color(PApplet.parseInt(userInfo[58]));
             
            upgrades[0] = PApplet.parseBoolean(userInfo[59]);
            upgrades[1] = PApplet.parseBoolean(userInfo[60]);
             
            ownedSticker[0] = userInfo[61];
            ownedSticker[1] = userInfo[62];
            ownedSticker[2] = userInfo[63];
            ownedSticker[3] = userInfo[64];
             
            selectedSticker[0] = userInfo[65];
            selectedSticker[1] = userInfo[66];
            
            sticker1 = PApplet.parseBoolean(userInfo[67]);
            sticker2 = PApplet.parseBoolean(userInfo[68]);

             
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




public void keyPressed () {

  
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




public void drawCharacter (int x, int y, float size) {          //Draws character given the x and y coordinates and desired size
  
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




public double decideArtValue (double time, int colourChange, int sizeChange, int stickerChange){          //Decides art value using time taken drawing, # of colour changes, 
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
    
    
    artValue = a + (Math.abs(colourChange*sizeChange)) + (stickerChange*0.2f);
    
    String formatted = dFormatter.format(artValue);
    
    artValueF = Double.parseDouble(formatted);
    
    return artValueF;
  
}




public void chosen (int paletteNum, double price) {              //Determines what palettes are owned by user/selected on the paint screen
  
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




public void chosenSticker (int stickerNum, double price) {              //Determines what stickers are owned by user/selected on the paint screen
  
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




public void mouseMoved () {
 
  println (mouseX + " " + mouseY + " " + show + " " + username);
  
}


public void draw () {

  
    if (show == "titlescreen") {
     
      
//Background Image                       https://i.pinimg.com/originals/28/3e/be/283ebe2a7a1e109bbae5f101ae674ffe.jpg

        image(img,0,0,width,height);
            
                
//Game Title
    
        fill(0);
        textFont(loadFont("Zapfino60.vlw"),100);
        textAlign(CENTER);
        text ("Paint Pro",718,180);
        
        
//"New Game" Button

        stroke (255);
        noFill();
        rect(480,300,480,200);
        textFont(f);
        textAlign(CENTER);
        text ("New Game", 718,415);
        
        
//"Continue Game" Button

        stroke (255);
        noFill();
        rect(480,600,480,200);
        textFont (f);
        textAlign(CENTER);
        text ("Continue Game", 718,715);
      
    }
    
    
    
    
    if (show == "characterSelect") {
      
      
//Character
      
        background (0);
        drawCharacter(358,350,1.5f);
         
         
//Break Lines

        stroke(255); 
        line (0,700,1440,700);
        line (720,0,720,700);
        
        
//Hair Colour Buttons

        fill (255);
        textFont (f,50);
        textAlign(LEFT);
        text("Hair:", 770, 58);
        
        stroke (255);
        fill (hairColour);
        rect(1205,28,30,30);
       
        stroke (255);
        fill (255);
        triangle (1130,43,1145,28,1145,58);
        triangle (1310,43,1295,28,1295,58);
        
        
//Hair Style Buttons

        fill (255);
        textFont (f,50);
        text("Hair Style:", 770, 144);
        
        stroke (255);
        fill (hairColour);
        rect(1205,114,30,30);
        
        stroke(0);
        fill (255);
        textFont(f,20);
        textAlign(CENTER,CENTER);
        text(str(hairStyles[hs]),1205,114,30,30);
        
        stroke (255);
        fill (255);
        triangle (1130,129,1145,114,1145,144);
        triangle (1310,129,1295,114,1295,144);
        
        
//Skin Colour Buttons

        fill (255);
        textFont (f,50);
        textAlign(LEFT);
        text("Skin:", 770, 230);
        
        fill (skinColour);
        rect(1205,200,30,30);
        
        stroke (255);
        fill (255);
        triangle (1130,215,1145,200,1145,230);
        triangle (1310,215,1295,200,1295,230);
        
    
//Eye Colour Buttons

        fill (255);
        textFont (f,50);
        text("Eyes:", 770, 316);
        
        fill (eyeColour);
        rect(1205,286,30,30);
        
        stroke (255);
        fill (255);
        triangle (1130,301,1145,286,1145,316);
        triangle (1310,301,1295,286,1295,316);
        
        
//Shirt Colour Buttons
 
        fill (255);
        textFont (f,50);
        text("Shirt:", 770, 402);
        
        fill (shirtColour); 
        rect(1205,372,30,30);
        
        stroke (255);
        fill (255);
        triangle (1130,387,1145,372,1145,402);
        triangle (1310,387,1295,372,1295,402);
        
        
//Pant Colour Buttons

        fill (255);
        textFont (f,50);
        text("Pants:", 770, 488);
        
        fill (pantsColour); 
        rect(1205,458,30,30);
        
        stroke (255);
        fill (255);
        triangle (1130,473,1145,458,1145,488);
        triangle (1310,473,1295,458,1295,488);
        
        
//Belt Colour Buttons

        fill (255);
        textFont (f,50);
        text("Belt:", 770, 574);
        
        fill (beltColour); 
        rect(1205,544,30,30);
        
        stroke (255);
        fill (255);
        triangle (1130,559,1145,544,1145,574);
        triangle (1310,559,1295,544,1295,574);
        
        
//Shoe Colour Buttons

        fill (255);
        textFont (f,50);
        text("Shoes:", 770, 660);
        
        fill (shoeColour);  
        rect(1205,630,30,30);
        
        stroke (255);
        fill (255);
        triangle (1130,645,1145,630,1145,660);
        triangle (1310,645,1295,630,1295,660);
        
          
//Done Character Select Button

        stroke (255);
        noFill();
        rect(1300,810,100,50);
        textFont(f,25);
        fill (255);
        textAlign(CENTER,CENTER);
        text("Done",1300,810,100,50);
        
        
//Choose Username Prompt

        if (noUsername == true) {
          
            textFont(f,15);
            textAlign(CENTER,CENTER);
            text("Please choose a username.",1195,755);
          
        }
        
           
//Username

        textAlign(LEFT,CENTER);
        textFont(f,60);
        text ("Name:",100,800);
        
        textFont(f,40);
        text(username,425,800);
    
    
//Username Box

        if (!namer) {
          
            stroke(0xff808080);
            noFill();
          
        }
        
        else {
         
            stroke(255);
            noFill();
          
        }
        
        rect (395,750,556,100);
      
    }
    
    
    
    
    if (show == "homescreen") {
     
      
//Character
      
        background (0);
        drawCharacter(720,450,2);
        
        
//Money
        
        fill(255);
        textFont(f,30);
        textAlign(LEFT,TOP);
        String formatted = dFormatter.format(money);
        money = Double.parseDouble(formatted);
        
        if (money == 0) {                              //if money is 0, display "$0.00" rather than "$0.0"
          
            text("Money:  $ "+money+"0",60,50);
            
        }
        
        else {
          
          text("Money:  $ "+money,60,50);
          
        }
       
        
//Paint Button

        stroke (255);
        noFill();
        rect(200,200,300,150);
        textFont(f);
        fill(255);
        textAlign(CENTER,CENTER);
        text("Paint",200,200,300,150);
        
        
//Full Gallery Warning
        
        if (fullGallery && (millis() - fTime <= 2000)) {
         
            textFont(f,20);
            fill(0xffff0000);
            textAlign(CENTER);
            text("Your gallery is full - please make room before creating another painting.",720,50);
            
            if (millis() - fTime <= 3) {
                
                fullGallery = false;
              
            }
          
        }
        
        
//Gallery Button

        stroke (255);
        noFill();
        rect(200,500,300,150);
        textFont(f);
        textAlign(CENTER,CENTER);
        fill(255);
        text("Gallery",200,500,300,150);
        
    
//Art Shop Button
    
        stroke (255);
        noFill();
        rect(940,200,300,150);
        textFont(f);
        text("Art Shop",940,200,300,150);
        
    
//Art Competition Button

        stroke (255);
        noFill();
        rect(940,500,300,150);
        textFont(f,40);
        text("Art\nCompetition",940,500,300,150);
        
    
//Save Game Button

        stroke (255);
        noFill();
        rect(1280,50,110,50);
        textFont(f,15);
        text("Save Game",1280,50,110,50);
        
        
//"Game Saved" text
        
        if (gamesave1 && (millis() - sTime <= 2000)) {
         
            textFont(f,20);
            textAlign(CENTER);
            text("Game saved.",720,50);
            
            if (millis() - sTime <= 3) {
                
                gamesave1 = false;
              
            }
          
        }
      
    }
    
    
    

    if (show == "paint") {
        
      
//Page Breaks
    
        strokeWeight(1);
        stroke(255);
        fill(0);
        rect (0,0,300,700);
        rect (0,700,1439,199);
      

//Palette 1

        fill(palette1[0]);
        rect(25,25,50,50);
        
        fill(palette1[1]);
        rect(75,25,50,50);
        
        fill(palette1[2]);
        rect(125,25,50,50);
        
        fill(palette1[3]);
        rect(175,25,50,50);
        
        fill(palette1[4]);
        rect(225,25,50,50);

        
//Palette 2

        fill(palette2[0]);
        rect(25,100,50,50);
        
        fill(palette2[1]);
        rect(75,100,50,50);
        
        fill(palette2[2]);
        rect(125,100,50,50);
        
        fill(palette2[3]);
        rect(175,100,50,50);
        
        fill(palette2[4]);
        rect(225,100,50,50);

        
//Palette 3

        fill(palette3[0]);
        rect(25,175,50,50);
        
        fill(palette3[1]);
        rect(75,175,50,50);
        
        fill(palette3[2]);
        rect(125,175,50,50);
        
        fill(palette3[3]);
        rect(175,175,50,50);
        
        fill(palette3[4]);
        rect(225,175,50,50);

            
//Palette 4

        fill(palette4[0]);
        rect(25,250,50,50);
        
        fill(palette4[1]);
        rect(75,250,50,50);
        
        fill(palette4[2]);
        rect(125,250,50,50);
        
        fill(palette4[3]);
        rect(175,250,50,50);
        
        fill(palette4[4]);
        rect(225,250,50,50);
        

//Sticker Boxes
        
        noFill();
        strokeWeight(1);
        if (sticker1) {
          
            strokeWeight(3);
          
        }
        rect(30,350,105,105);


        strokeWeight(1);
        if (sticker2) {
          
            strokeWeight(3);
          
        }
        rect(165,350,105,105);
        
        
//Selected Sticker Display
   
        if (!(selectedSticker[0].equals(""))) {
        
            img = loadImage(selectedSticker[0]);
            image (img,35,355,95,95);
        
        }

        if (!(selectedSticker[1].equals(""))) {
          
            img = loadImage(selectedSticker[1]);
            image(img,170,355,95,95);
        
        }
        

//Paint Sizes

        if (upgrades[0]){                  //if Paint Sizes upgrade has been purchased
           
          
            //Small Circle
        
        
            strokeWeight(3);
            
            if (paintSize == 10) {
              
                strokeWeight(5);
              
            }
            
            stroke (255);
            fill (paintColour);
            ellipse (140,530,10,10);
            
            
            //Large Circle
            
            
            strokeWeight(3);
            
            if (paintSize == 60) {
              
              strokeWeight(5);
              
            }
            
            stroke (255);
            fill (paintColour);
            ellipse (245,530,60,60);
            
        }
        
        
        //Medium Circle
        
        
        strokeWeight(3);
        
        if (paintSize == 30) {
          
          strokeWeight(5);
          
        }
        
        stroke (255);
        fill (paintColour);
        ellipse (180,530,30,30);
        

//Eraser Button

        strokeWeight(1);
        stroke (255);
        fill (backColour);
        rect (32,505,70,50);
        textFont (f,15);
        fill(255);
        textAlign(CENTER,CENTER);
        text ("Eraser", 32,505,70,50);
        
        
//Colour Mixer

        if (upgrades[1]){                            //if Colour Mixer upgrade has been purchased
        
        
            //Chosen Colour Button
            
            
            stroke (255);
            fill (chosenR,chosenG,chosenB);
            rect (40,610,50,50);
            textFont (f);
            fill(255);
            
            
            //RGB Buttons
            
            
            stroke(255);
            noFill();
            triangle (130,620,145,590,160,620);
            triangle (185,620,200,590,215,620);
            triangle (240,620,255,590,270,620);
            
            triangle (130,650,145,680,160,650);
            triangle (185,650,200,680,215,650);
            triangle (240,650,255,680,270,650);
            
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("R",146,635);
            text ("G",201,635);
            text ("B",256,635);
        
        }
        
        
//Save and Finish Painting Button

        stroke (255);
        noFill();
        rect(1250,810,150,50);
        textFont(f,25);
        fill (255);
        textAlign(CENTER,CENTER);
        text("Finish Art",1250,810,150,50);
        
        
//Quit Button

        stroke (255);
        noFill();
        rect(1060,810,150,50);
        textFont(f,25);
        fill (255);
        textAlign(CENTER,CENTER);
        text("Quit",1060,810,150,50);
        
        
//Choose Title Prompt

        if (noTitle == true) {
          
            textFont(f,15);
            textAlign(CENTER,CENTER);
            text("Please create a title for your art.",1195,755);
          
        }
        
        
//Art Title

        textAlign(CENTER,CENTER);
        textFont(f,60);
        text ("Art Title:",198,800);
        
        textAlign(LEFT,CENTER);
        textFont(f,40);
        text(title,425,800);
    
    
//Art Title Box

        if (!namer) {
          
            stroke(0xff808080);
            noFill();
          
        }
        
        else {
         
            stroke(255);
            noFill();
          
        }
        
        strokeWeight(3);
        rect (395,750,556,100);
        
        
        if (mousePressed) {              //needed in void draw() to perform painting function
          
          
//Palette 1

            if (mouseX > 25 && mouseX < 25+50 && mouseY > 25 && mouseY < 25+50 && (palette1[0] != paletteEmpty[0])) {
                 
                    paintColour = palette1[0];

            }
            
            else if (mouseX > 75 && mouseX < 75+50 && mouseY > 25 && mouseY < 25+50 && (palette1[0] != paletteEmpty[0])) {
             
                    paintColour = palette1[1];
                    
            }
            
            else if (mouseX > 125 && mouseX < 125+50 && mouseY > 25 && mouseY < 25+50 && (palette1[0] != paletteEmpty[0])) {
              
                    paintColour = palette1[2];

            }
            
            else if (mouseX > 175 && mouseX < 175+50 && mouseY > 25 && mouseY < 25+50 && (palette1[0] != paletteEmpty[0])) {
              
                    paintColour = palette1[3];
              
            }
            
            
            else if (mouseX > 225 && mouseX < 225+50 && mouseY > 25 && mouseY < 25+50 && (palette1[0] != paletteEmpty[0])){
             
                    paintColour = palette1[4];
              
            }
            
          
//Palette 2
    
            else if (mouseX > 25 && mouseX < 25+50 && mouseY > 100 && mouseY < 100+50 && (palette2[0] != paletteEmpty[0])) {
                 
                    paintColour = palette2[0];

            }
            
            else if (mouseX > 75 && mouseX < 75+50 && mouseY > 100 && mouseY < 100+50 && (palette2[0] != paletteEmpty[0])) {
             
                    paintColour = palette2[1];
                    
            }
            
            else if (mouseX > 125 && mouseX < 125+50 && mouseY > 100 && mouseY < 100+50 && (palette2[0] != paletteEmpty[0])) {
              
                    paintColour = palette2[2];

            }
            
            else if (mouseX > 175 && mouseX < 175+50 && mouseY > 100 && mouseY < 100+50 && (palette2[0] != paletteEmpty[0])) {
              
                    paintColour = palette2[3];
              
            }
            
            
            else if (mouseX > 225 && mouseX < 225+50 && mouseY > 100 && mouseY < 100+50 && (palette2[0] != paletteEmpty[0])){
             
                    paintColour = palette2[4];
              
            }
            
             
//Palette 3

            else if (mouseX > 25 && mouseX < 25+50 && mouseY > 175 && mouseY < 175+50 && (palette3[0] != paletteEmpty[0])) {
                 
                    paintColour = palette3[0];

            }
            
            else if (mouseX > 75 && mouseX < 75+50 && mouseY > 175 && mouseY < 175+50 && (palette3[0] != paletteEmpty[0])) {
             
                    paintColour = palette3[1];
                    
            }
            
            else if (mouseX > 125 && mouseX < 125+50 && mouseY > 175 && mouseY < 175+50 && (palette3[0] != paletteEmpty[0])) {
              
                    paintColour = palette3[2];

            }
            
            else if (mouseX > 175 && mouseX < 175+50 && mouseY > 175 && mouseY < 175+50 && (palette3[0] != paletteEmpty[0])) {
              
                    paintColour = palette3[3];
              
            }
            
            
            else if (mouseX > 225 && mouseX < 225+50 && mouseY > 175 && mouseY < 175+50 && (palette3[0] != paletteEmpty[0])){
             
                    paintColour = palette3[4];
              
            }
            
            
//Palette 4

            else if (mouseX > 25 && mouseX < 25+50 && mouseY > 250 && mouseY < 250+50 && (palette4[0] != paletteEmpty[0])) {
                 
                    paintColour = palette4[0];

            }
            
            else if (mouseX > 75 && mouseX < 75+50 && mouseY > 250 && mouseY < 250+50 && (palette4[0] != paletteEmpty[0])) {
             
                    paintColour = palette4[1];
                    
            }
            
            else if (mouseX > 125 && mouseX < 125+50 && mouseY > 250 && mouseY < 250+50 && (palette4[0] != paletteEmpty[0])) {
              
                    paintColour = palette4[2];

            }
            
            else if (mouseX > 175 && mouseX < 175+50 && mouseY > 250 && mouseY < 250+50 && (palette4[0] != paletteEmpty[0])) {
              
                    paintColour = palette4[3];
              
            }
            
            
            else if (mouseX > 225 && mouseX < 225+50 && mouseY > 250 && mouseY < 250+50 && (palette4[0] != paletteEmpty[0])){
             
                    paintColour = palette4[4];
              
            }      

      
//Eraser
    
            else if (mouseX > 32 && mouseX < 32+70 && mouseY > 500 && mouseY < 500+50) {
              
                paintColour = backColour;
          
            }
            
              
//Paint Sizes

            else if (mouseX > 135 && mouseX < 135+10 && mouseY > 525 && mouseY < 525+10 && upgrades[0]) {
        
                paintSize = 10;
          
            }
            
            else if (mouseX > 165 && mouseX < 165+30 && mouseY > 515 && mouseY < 515+30 && upgrades[0]) {
        
                paintSize = 30;
          
            }
            
            else if (mouseX > 215 && mouseX < 215+60 && mouseY > 500 && mouseY < 500+60 && upgrades[0]) {
        
                paintSize = 60;
          
            }
            
               
//Colour Mixer

            else if (mouseX > 40 && mouseX < 40+50 && mouseY > 610 && mouseY < 610+50 && upgrades[1]) {
              
                paintColour = color(chosenR,chosenG,chosenB);
              
            }
           
            else if (mouseX > 130 && mouseX < 130+30 && mouseY > 590 && mouseY < 590+30 && upgrades[1]){
             
                chosenR++;
              
            }
            
            else if (mouseX > 185 && mouseX < 185+30 && mouseY > 590 && mouseY < 590+30 && upgrades[1]){
             
                chosenG++;
              
            }
            
            else if (mouseX > 240 && mouseX < 240+30 && mouseY > 590 && mouseY < 590+30 && upgrades[1]){
             
                chosenB++;
              
            }
            
            else if (mouseX > 130 && mouseX < 130+30 && mouseY > 650 && mouseY < 650+30 && upgrades[1]){
             
                chosenR--;
              
            }
            
            else if (mouseX > 185 && mouseX < 185+30 && mouseY > 650 && mouseY < 650+30 && upgrades[1]){
             
                chosenG--;
              
            }
            
            else if (mouseX > 240 && mouseX < 240+30 && mouseY > 650 && mouseY < 650+30 && upgrades[1]){
             
                chosenB--;
              
            }
            
            
//Stickers
            
            else if (sticker1) {
              
                img = loadImage (selectedSticker[0]);
                image (img,mouseX-47.5f,mouseY-47.5f,95,95);
                
                if (selectedSticker[0].equals("chrisevans.png") && mouseY < 700){
                  
                    fileChris.play();
                  
                }
              
            }
            
            else if (sticker2) {
              
                img = loadImage (selectedSticker[1]);
                image (img,mouseX-47.5f,mouseY-47.5f,95,95);
                
                if (selectedSticker[0].equals("chrisevans.png") && mouseY < 700){
                  
                    fileChris.play();
                  
                }
              
            }
            

//Paint
            
            else {
                
                strokeWeight(1);
                stroke (paintColour);
                fill (paintColour);
                ellipse(mouseX, mouseY, paintSize, paintSize);
              
            }
          
        }
        
    }
    
    
    

    if (show == "gallery") {
      
      
        background(0);
        
        
//Heading

        textFont(loadFont("Zapfino60.vlw"),40);
        textAlign(CENTER,CENTER);
        text (username + "'s Gallery",720,80);
        
        
//Break Lines

        stroke(255);
        strokeWeight(3);
        line(30,190,30,780);
        line(30,190,1410,190);
        line(1410,190,1410,780);
        line(30,780,1410,780);
        
        line(490,190,490,780);
        line(950,190,950,780);
        
        
//"Back To Homescreen" Button
    
        noFill();
        rect(1260,805,150,70);
        textFont(f,20);
        textAlign(CENTER,CENTER);
        text ("Back to Homescreen",1260,805,150,70);
        
        
//Painting 1
        
        if (paintings[0]) {
        
          
            //Frame
            
            
            stroke(255);
            strokeWeight(1);
            fill(255);
            rect(60,240,(1140/3)+20,(700/3)+20);
          
          
            //Painting
            
            
            img = loadImage("painting-0000.png");
            PImage painting1 = img.get(301,0,1140,700);          //Crops the saved image of the paint screen w/ the painting
            image (painting1,70,250,1140/3,700/3);
            
            
            //Tilte/Value Display
            
            
            textFont(f,30);
            textAlign(LEFT,CENTER);
            text("Title: ",90,540);
            text ("Value: ",90,590);
            
            textAlign(LEFT,CENTER);
            text(artTitle[0],220,540);
            text("$ " +artValue[0],220,590);
            
            
            //"Sell" Button
            
            
            stroke(255);
            strokeWeight(3);
            noFill();
            rect(83,675,150,75);
            fill(255);
            textFont(f,30);
            textAlign(CENTER,CENTER);
            text("Sell",83,675,150,75);
        
        
            //"Enter in Competition" Button
            
            
            stroke(255);
            strokeWeight(3);
            noFill();
            rect(286,675,150,75);
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text("Enter in Competition",286,675,150,75);
    
        }
        
        
//Painting 2
        
        if (paintings[1]) {
        
          
            //Frame
            
            
            stroke(255);
            fill(255);
            rect(520,240,(1140/3)+20,(700/3)+20);
          
          
            //Painting
            
            
            img = loadImage("painting-0001.png");
            PImage painting2 = img.get(301,0,1140,700);
            image (painting2,530,250,1140/3,700/3);


            //Title/Value Display
            
            
            textFont(f,30);
            textAlign(LEFT,CENTER);
            text("Title: ",550,540);
            text ("Value: ",550,590);
            
            textAlign(LEFT,CENTER);
            text(artTitle[1],680,540);
            text("$ " +artValue[1],680,590);


            //"Sell" Button
            
            
            stroke(255);
            strokeWeight(3);
            noFill();
            rect(543,675,150,75);
            fill(255);
            textFont(f,30);
            textAlign(CENTER,CENTER);
            text("Sell",543,675,150,75);
        
        
            //"Enter in Competition" Button
            
            
            stroke(255);
            strokeWeight(3);
            noFill();
            rect(746,675,150,75);
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text("Enter in Competition",746,675,150,75);
        
        }
        
        
//Painting 3
        
        if (paintings[2]) {
        
          
            //Frame
            
            
            stroke(255);
            fill(255);
            rect(980,240,(1140/3)+20,(700/3)+20);
          
          
            //Painting
            
            
            img = loadImage("painting-0002.png");
            PImage painting3 = img.get(301,0,1140,700);
            image (painting3,990,250,1140/3,700/3);
            
            
            //Title/Value Display
            
            
            textFont(f,30);
            textAlign(LEFT,CENTER);
            text("Title: ",1010,540);
            text ("Value: ",1010,590);
            
            textAlign(LEFT,CENTER);
            text(artTitle[2],1140,540);
            text("$ " +artValue[2],1140,590);


            //"Sell" Button
            
            
            stroke(255);
            strokeWeight(3);
            noFill();
            rect(1003,675,150,75);
            fill(255);
            textFont(f,30);
            textAlign(CENTER,CENTER);
            text("Sell",1003,675,150,75);
        
        
            //"Enter in Competition" Button
            
            
            stroke(255);
            strokeWeight(3);
            noFill();
            rect(1206,675,150,75);
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text("Enter in Competition",1206,675,150,75);
        
        }
      
    }
    
    
    

    if (show == "shop1") {
      
      
        background(0);
      
      
//Money
      
        fill(255);
        textFont(f,30);
        textAlign(LEFT,TOP);
        String formatted = dFormatter.format(money);
        money = Double.parseDouble(formatted);
        
        if (money == 0) {
          
            text("Money:  $ "+money+"0",60,50);
            
        }
        
        else {
          
          text("Money:  $ "+money,60,50);
          
        }
      
      
//Break Lines

        stroke(255);
        strokeWeight(3);
        line(30,190,30,780);
        line(30,190,1410,190);
        line(1410,190,1410,780);
        line(30,780,1410,780);
        
        line(960,190,960,780);
        line(960,485,1410,485);
     
     
//"Back To Homescreen" Button
    
        noFill();
        rect(1260,805,150,70);
        fill(255);
        textFont(f,20);
        textAlign(CENTER,CENTER);
        text ("Back to Homescreen",1260,805,150,70);
        
        
//"Art Shop" Heading

        textFont(loadFont("Zapfino60.vlw"),40);
        textAlign(CENTER,CENTER);
        text ("Art Shop",720,80);
        
 
//"Not enough money" prompt
    
        if (notEnough && (millis() - mTime <= 2000)) {
        
            fill(0xffff0000);
            textFont(f,20);
            textAlign(LEFT,CENTER);
            text ("Not enough money.",60,130);
            
            if (millis() - mTime <= 3) {
             
                notEnough = false;
              
            }
            
        }
        
        
//Arrow keys (navigate b/w shop1, shop 2)
    
        stroke(255);
        noFill();
        rect(770,672,130,50);
        
        fill(255);
        textFont(f,16);
        textAlign(CENTER,CENTER);
        text("1/2",770,672,130,50);
        
        triangle(780,697,805,682,805,712);
        triangle(890,697,865,682,865,712);
        
  
//"Palletes" text
        
        fill(255);
        textFont(f,40);
        textAlign(LEFT,TOP);
        text ("Palettes:",90,230);
        
        textFont(f,20);
        textAlign(CENTER,CENTER);
        text ("*You may select up to 4 palettes at once.*",595,250);
        

//Palette 1
  
        strokeWeight(1);
        fill(palettes[0][0]);
        rect(90,320,50,50);
        
        fill(palettes[0][1]);
        rect(140,320,50,50);
        
        fill(palettes[0][2]);
        rect(190,320,50,50);
        
        fill(palettes[0][3]);
        rect(240,320,50,50);
        
        fill(palettes[0][4]);
        rect(290,320,50,50);
        
        
        //Price/Owned Box
        
        
        if (owned[0]) {
          
            noFill();
            rect(90,390,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("Owned",90,390,117,50);
          
        }
        
        if (!(owned[0])) {
          
            noFill();
            rect(90,390,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("$0.00",90,390,117,50);
          
        }


        //Buy/Select/Selected Box
        
        
        if (selected[0]) {
          
            fill(0xff00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",223,390,117,50);
            
            stroke(0xff00ff00);
          
        }
        
        if (!(selected[0]) && owned[0]) {
          
            fill(0xffffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Select",223,390,117,50);
            
            stroke(0xffffffff);
          
        }
        
        noFill();
        rect(223,390,117,50);
        
        
//Palette 2
    
        stroke(0xffffffff);
        strokeWeight(1);
        fill(palettes[1][0]);
        rect(90,460,50,50);
        
        fill(palettes[1][1]);
        rect(140,460,50,50);
        
        fill(palettes[1][2]);
        rect(190,460,50,50);
        
        fill(palettes[1][3]);
        rect(240,460,50,50);
        
        fill(palettes[1][4]);
        rect(290,460,50,50);


        //Price/Owned Box
        
        
        if (owned[1]) {
            
            stroke(0xffffffff);
            noFill();
            rect(90,530,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("Owned",90,530,117,50);
          
        }
        
        if (!(owned[1])) {
          
            stroke(0xffffffff);
            noFill();
            rect(90,530,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("$100.00",90,530,117,50);
          
        }
        
        
        //Buy/Select/Selected Box
        
        
        if (!(owned[1])) {
          
            stroke(0xffffffff);
            noFill();
            rect(223,530,117,50);
          
            fill(0xffffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",223,530,117,50);
          
        }
        
        if (selected[1]) {
            
            stroke(0xff00ff00);
            noFill();
            rect(223,530,117,50);
            
            fill(0xff00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",223,530,117,50);

        }
        
        if (!(selected[1]) && owned[1]) {
          
            stroke(0xffffffff);
            noFill();
            rect(223,530,117,50);
          
            fill(0xffffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Select",223,530,117,50);
          
        }

        
//Palette 3

        stroke(0xffffffff);
        strokeWeight(1);
        fill(palettes[2][0]);
        rect(90,602,50,50);
        
        fill(palettes[2][1]);
        rect(140,602,50,50);
        
        fill(palettes[2][2]);
        rect(190,602,50,50);
        
        fill(palettes[2][3]);
        rect(240,602,50,50);
        
        fill(palettes[2][4]);
        rect(290,602,50,50);


        //Price/Owned Box
        
        
        if (owned[2]) {
          
            noFill();
            rect(90,672,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("Owned",90,672,117,50);
          
        }
        
        if (!(owned[2])) {
          
            stroke(0xffffffff);
            noFill();
            rect(90,672,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("$500.00",90,672,117,50);
          
        }


        //Buy/Select/Selected Box
        
        
        if (!(owned[2])) {
          
            stroke(0xffffffff);
            noFill();
            rect(223,672,117,50);
          
            fill(0xffffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",223,672,117,50);
          
        }

        if (selected[2]) {
            
            stroke(0xff00ff00);
            noFill();
            rect(223,672,117,50);
            
            fill(0xff00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",223,672,117,50);
 
        }
        
        if (!(selected[2]) && owned[2]) {
          
            stroke(0xffffffff);
            noFill();
            rect(223,672,117,50);
          
            fill(0xffffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Select",223,672,117,50);
          
        }
        

//Palette 4

        stroke(0xffffffff);
        strokeWeight(1);
        fill(palettes[3][0]);
        rect(460,320,50,50);
        
        fill(palettes[3][1]);
        rect(510,320,50,50);
        
        fill(palettes[3][2]);
        rect(560,320,50,50);
        
        fill(palettes[3][3]);
        rect(610,320,50,50);
        
        fill(palettes[3][4]);
        rect(660,320,50,50);
 
 
        //Price/Owned Box
        
        
        if (owned[3]) {
          
            noFill();
            rect(460,390,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("Owned",460,390,117,50);
          
        }
        
        if (!(owned[3])) {
          
            stroke(0xffffffff);
            noFill();
            rect(460,390,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("$250.00",460,390,117,50);
          
        }


        //Buy/Select/Selected Box
        
        
        if (!(owned[3])) {
          
            stroke(0xffffffff);
            noFill();
            rect(593,390,117,50);
          
            fill(0xffffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",593,390,117,50);
          
        }
 
        if (selected[3]) {
            
            stroke(0xff00ff00);
            noFill();
            rect(593,390,117,50);
            
            fill(0xff00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",593,390,117,50);

        }
        
        if (!(selected[3]) && owned[3]) {
          
            stroke(0xffffffff);
            noFill();
            rect(593,390,117,50);
          
            fill(0xffffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Select",593,390,117,50);
          
        }
        
   
//Palette 5

        stroke(0xffffffff);
        strokeWeight(1);
        fill(palettes[4][0]);
        rect(460,460,50,50);
        
        fill(palettes[4][1]);
        rect(510,460,50,50);
        
        fill(palettes[4][2]);
        rect(560,460,50,50);
        
        fill(palettes[4][3]);
        rect(610,460,50,50);
        
        fill(palettes[4][4]);
        rect(660,460,50,50);


        //Price/Owned Box
        
        
        if (owned[4]) {
          
            noFill();
            rect(460,530,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("Owned",460,530,117,50);
          
        }
        
        if (!(owned[4])) {
          
            stroke(0xffffffff);
            noFill();
            rect(460,530,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("$250.00",460,530,117,50);
          
        }


        //Buy/Select/Selected Box
        
        
        if (!(owned[4])) {
          
            stroke(0xffffffff);
            noFill();
            rect(593,530,117,50);
          
            fill(0xffffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",593,530,117,50);
          
        }

        if (selected[4]) {
            
            stroke(0xff00ff00);
            noFill();
            rect(593,530,117,50);
            
            fill(0xff00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",593,530,117,50);

        }
        
        if (!(selected[4]) && owned[4]) {
          
            stroke(0xffffffff);
            noFill();
            rect(593,530,117,50);
          
            fill(0xffffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Select",593,530,117,50);
          
        }
        
 
//Palette 6

        stroke(0xffffffff);
        strokeWeight(1);
        fill(palettes[5][0]);
        rect(460,602,50,50);
        
        fill(palettes[5][1]);
        rect(510,602,50,50);
        
        fill(palettes[5][2]);
        rect(560,602,50,50);
        
        fill(palettes[5][3]);
        rect(610,602,50,50);
        
        fill(palettes[5][4]);
        rect(660,602,50,50);


        //Price/Owned Box
        
        
        if (owned[5]) {
          
            stroke(0xffffffff);
            noFill();
            rect(460,672,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("Owned",460,672,117,50);
          
        }
        
        if (!(owned[5])) {
          
            noFill();
            rect(460,672,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("$500.00",460,672,117,50);
          
        }


        //Buy/Select/Selected Box
        
        
        if (!(owned[5])) {
          
            stroke(0xffffffff);
            noFill();
            rect(593,672,117,50);
          
            fill(0xffffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",593,672,117,50);
          
        }
        
        if (selected[5]) {
            
            stroke(0xff00ff00);
            noFill();
            rect(593,672,117,50);
            
            fill(0xff00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",593,672,117,50);

        }
        
        if (!(selected[5]) && owned[5]) {
          
            stroke(0xffffffff);
            noFill();
            rect(593,672,117,50);
          
            fill(0xffffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Select",593,672,117,50);
          
        }
 
        
//Paint Sizes Upgrade

        fill(255);
        textFont(f,29);
        textAlign(LEFT,TOP);
        text ("Paint Sizes - $100.00",1000,220);
        
        fill(255);
        textFont(f,16);
        textAlign(LEFT,CENTER);
        text ("Add 2 more paint sizes to your drawing \ntools, and make more complicated \npaintings!",1000,315);

        stroke(255);
        noFill();
        strokeWeight(1);
        rect(1110,395,150,50);
        
        if (!(upgrades[0])) {
          
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",1110,395,150,50);
        
        }
        
        if (upgrades[0]) {
          
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Owned",1110,395,150,50);
        
        }
        
 
//Colour Mixer Upgrade

        fill(255);
        textFont(f,29);
        textAlign(LEFT,TOP);
        text ("Colour Mixer - $1,000.00",1000,515);
        
        fill(255);
        textFont(f,16);
        textAlign(LEFT,CENTER);
        text ("Use RGB buttons to mix and create new \ncolours while painting. Create more \ndetailed art!",1000,615);

        stroke(255);
        noFill();
        strokeWeight(1);
        rect(1110,690,150,50);

        if (!(upgrades[1])) {
          
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",1110,690,150,50);
        
        }
        
        if (upgrades[1]) {
          
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Owned",1110,690,150,50);
        
        }

    }
    
    
    
    
    if (show == "shop2") {
      
      
        background(0);
      
      
//Money

        fill(255);
        textFont(f,30);
        textAlign(LEFT,TOP);
        String formatted = dFormatter.format(money);
        money = Double.parseDouble(formatted);
        
        if (money == 0) {
          
            text("Money:  $ "+money+"0",60,50);
            
        }
        
        else {
          
          text("Money:  $ "+money,60,50);
          
        }
        
      
//Break Lines

        stroke(255);
        strokeWeight(3);
        line(30,190,30,780);
        line(30,190,1410,190);
        line(1410,190,1410,780);
        line(30,780,1410,780);
        
        line(960,190,960,780);
        line(960,485,1410,485);
     
     
//"Back To Homescreen" Button
    
        noFill();
        rect(1260,805,150,70);
        fill(255);
        textFont(f,20);
        textAlign(CENTER,CENTER);
        text ("Back to Homescreen",1260,805,150,70);
        
        
//"Art Shop" Heading

        textFont(loadFont("Zapfino60.vlw"),40);
        textAlign(CENTER,CENTER);
        text ("Art Shop",720,80);
     
        
//"Not enough money" prompt
    
        if (notEnough && (millis() - mTime <= 2000)) {
        
            fill(0xffff0000);
            textFont(f,20);
            textAlign(LEFT,CENTER);
            text ("Not enough money.",60,130);
            
            if (millis() - mTime <= 3) {
             
                notEnough = false;
              
            }
            
        }
        
  
//Arrow keys (navigate b/w shop 1, shop 2)
    
        stroke(255);
        noFill();
        rect(770,672,130,50);
        
        fill(255);
        textFont(f,16);
        textAlign(CENTER,CENTER);
        text("2/2",770,672,130,50);
        
        triangle(780,697,805,682,805,712);
        triangle(890,697,865,682,865,712);
        
        
//"Stickers" text
        
        fill(255);
        textFont(f,40);
        textAlign(LEFT,TOP);
        text ("Stickers:",90,230);
        
        textFont(f,20);
        textAlign(CENTER,CENTER);
        text ("*You may select up to 2 stickers at once.*",595,250);
    

//Sticker 1 (Star)

        noFill();
        strokeWeight(1);
        stroke(255);
        rect(195,320,105,105);
        
        img = loadImage("star.png");
        image(img,200,325,95,95);
    
    
        //Price/Owned Button
        
        
        noFill();
        stroke(255);
        rect(140,450,100,50);
        
        fill(255);
        textFont(f,20);
        textAlign(CENTER,CENTER);
        
        if (!(owned[6])) {
          
            text ("$50.00",140,450,100,50);
            
        }
        
        if (owned[6]) {
          
            text ("Owned",140,450,100,50);
        
        }


        //Buy/Select/Selected Button 
        
        
        strokeWeight(1);
        stroke(255);
        
        if (!(owned[6])) {
          
            text ("Buy",255,450,100,50);
          
        }
        
        if (owned[6] && !(selected[6])) {
          
            text ("Select",255,450,100,50);
          
        }
        
        if (owned[6] && selected[6]) {
            
            fill(0xff00ff00);
            text ("Selected",255,450,100,50);
            
            stroke (0xff00ff00);
          
        }
        
        noFill();
        rect(255,450,100,50);
        

//Sticker 2 (Heart)

        strokeWeight(1);
        stroke(255);
        rect(195,560,105,105);
        
        img = loadImage("heart.png");
        image(img,200,565,95,95);
  
  
        //Price/Owned Button
        
        
        noFill();
        stroke(255);
        rect(140,690,100,50);
        
        fill(255);
        textFont(f,20);
        textAlign(CENTER,CENTER);
        
        if (!(owned[7])) {
          
            text ("$50.00",140,690,100,50);
            
        }
        
        if (owned[7]) {
          
            text ("Owned",140,690,100,50);
        
        }


        //Buy/Select/Selected Button 
        
        
        noFill();
        strokeWeight(1);
        stroke(255);
        
        if (!(owned[7])) {
          
            text ("Buy",255,690,100,50);
          
        }
        
        if (owned[7] && !(selected[7])) {
          
            text ("Select",255,690,100,50);
          
        }
        
        if (owned[7] && selected[7]) {
            
            fill(0xff00ff00);
            text ("Selected",255,690,100,50);
            
            stroke (0xff00ff00);
          
        }

        noFill();
        rect(255,690,100,50);


//Sticker 3 (smiley face)

        strokeWeight(1);
        stroke(255);
        rect(510,320,105,105);
        
        img = loadImage("smileyface.png");
        image(img,515,325,95,95);
        
        
        //Price/Owned Button
        
        
        rect(455,450,100,50);
        
        fill(255);
        textFont(f,20);
        textAlign(CENTER,CENTER);
        
        if (!(owned[8])) {
          
            text ("$150.00",455,450,100,50);
            
        }
        
        if (owned[8]) {
          
            text ("Owned",455,450,100,50);
        
        }


        //Buy/Select/Selected Button 
        
        
        noFill();
        strokeWeight(1);
        stroke(255);
        
        if (!(owned[8])) {
          
            text ("Buy",570,450,100,50);
          
        }
        
        if (owned[8] && !(selected[8])) {
          
            text ("Select",570,450,100,50);
          
        }
        
        if (owned[8] && selected[8]) {
            
            fill(0xff00ff00);
            text ("Selected",570,450,100,50);
            
            stroke (0xff00ff00);
          
        }

        noFill();
        rect(570,450,100,50);

       
//Sticker 4 (Chris Evans)
 
        strokeWeight(1);
        stroke(255);
        rect(510,560,105,105);
        
        img = loadImage("chrisevans.png");
        image(img,515,565,95,95);


        //Price/Owned Button
        
        
        rect(455,690,100,50);
        
        fill(255);
        textFont(f,20);
        textAlign(CENTER,CENTER);
        
        if (!(owned[9])) {
            
            textFont(f,15);
            text ("$10,000.00",455,690,100,50);
            
        }
        
        if (owned[9]) {
          
            text ("Owned",455,690,100,50);
        
        }


        //Buy/Select/Selected Button 
        
        
        noFill();
        strokeWeight(1);
        stroke(255);
        
        if (!(owned[9])) {
            
            textFont(f,20);
            text ("Buy",570,690,100,50);
          
        }
        
        if (owned[9] && !(selected[9])) {
          
            textFont(f,20);
            text ("Select",570,690,100,50);
          
        }
        
        if (owned[9] && selected[9]) {
            
            textFont(f,20);
            fill(0xff00ff00);
            text ("Selected",570,690,100,50);
            
            stroke (0xff00ff00);
          
        }
        
        noFill();
        rect(570,690,100,50);
            
        
//Paint Sizes Upgrade

        fill(255);
        textFont(f,29);
        textAlign(LEFT,TOP);
        text ("Paint Sizes - $100.00",1000,220);
        
        fill(255);
        textFont(f,16);
        textAlign(LEFT,CENTER);
        text ("Add 2 more paint sizes to your drawing \ntools, and make more complicated \npaintings!",1000,315);
        
        stroke(255);
        noFill();
        strokeWeight(1);
        rect(1110,395,150,50);
        
        if (!(upgrades[0])) {
          
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",1110,395,150,50);
        
        }
        
        if (upgrades[0]) {
          
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Owned",1110,395,150,50);
        
        }
        
        
//Colour Mixer Upgrade

        fill(255);
        textFont(f,29);
        textAlign(LEFT,TOP);
        text ("Colour Mixer - $1,000.00",1000,515);
        
        fill(255);
        textFont(f,16);
        textAlign(LEFT,CENTER);
        text ("Use RGB buttons to mix and create new \ncolours while painting. Create more \ndetailed art!",1000,615);
        
        stroke(255);
        noFill();
        strokeWeight(1);
        rect(1110,690,150,50);

        if (!(upgrades[1])) {
          
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",1110,690,150,50);
        
        }
        
        if (upgrades[1]) {
          
            fill(255);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Owned",1110,690,150,50);
        
        }
      
    }
    
    


    if (show == "comp") {
     
      
//"Back To Homescreen" Button

        noFill();
        rect(1260,805,150,70);
        textFont(f,20);
        textAlign(CENTER,CENTER);
        text ("Back to Homescreen",1260,805,150,70);
        
        
//"Coming Soon!" text

        textFont(loadFont("Zapfino60.vlw"),50);
        textAlign(CENTER,CENTER);
        text ("Coming soon!",width/2,height/2-200);
        
        
//Hint

        textFont(f,30);
        textAlign(CENTER,CENTER);
        text ("For now: a hint!\n\nFor increased value on your paintings, take your time and\nuse multiple colours/sizes/stickers to create a masterpiece!",width/2,height/2);
    
    }
  
}
  public void settings() {  fullScreen();  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "PaintPro" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
