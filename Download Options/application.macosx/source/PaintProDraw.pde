

void draw () {

  
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
        drawCharacter(358,350,1.5);
         
         
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
          
            stroke(#808080);
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
            fill(#ff0000);
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
          
            stroke(#808080);
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
                image (img,mouseX-47.5,mouseY-47.5,95,95);
                
                if (selectedSticker[0].equals("chrisevans.png") && mouseY < 700){
                  
                    fileChris.play();
                  
                }
              
            }
            
            else if (sticker2) {
              
                img = loadImage (selectedSticker[1]);
                image (img,mouseX-47.5,mouseY-47.5,95,95);
                
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
        
            fill(#ff0000);
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
          
            fill(#00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",223,390,117,50);
            
            stroke(#00ff00);
          
        }
        
        if (!(selected[0]) && owned[0]) {
          
            fill(#ffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Select",223,390,117,50);
            
            stroke(#ffffff);
          
        }
        
        noFill();
        rect(223,390,117,50);
        
        
//Palette 2
    
        stroke(#ffffff);
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
            
            stroke(#ffffff);
            noFill();
            rect(90,530,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("Owned",90,530,117,50);
          
        }
        
        if (!(owned[1])) {
          
            stroke(#ffffff);
            noFill();
            rect(90,530,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("$100.00",90,530,117,50);
          
        }
        
        
        //Buy/Select/Selected Box
        
        
        if (!(owned[1])) {
          
            stroke(#ffffff);
            noFill();
            rect(223,530,117,50);
          
            fill(#ffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",223,530,117,50);
          
        }
        
        if (selected[1]) {
            
            stroke(#00ff00);
            noFill();
            rect(223,530,117,50);
            
            fill(#00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",223,530,117,50);

        }
        
        if (!(selected[1]) && owned[1]) {
          
            stroke(#ffffff);
            noFill();
            rect(223,530,117,50);
          
            fill(#ffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Select",223,530,117,50);
          
        }

        
//Palette 3

        stroke(#ffffff);
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
          
            stroke(#ffffff);
            noFill();
            rect(90,672,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("$500.00",90,672,117,50);
          
        }


        //Buy/Select/Selected Box
        
        
        if (!(owned[2])) {
          
            stroke(#ffffff);
            noFill();
            rect(223,672,117,50);
          
            fill(#ffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",223,672,117,50);
          
        }

        if (selected[2]) {
            
            stroke(#00ff00);
            noFill();
            rect(223,672,117,50);
            
            fill(#00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",223,672,117,50);
 
        }
        
        if (!(selected[2]) && owned[2]) {
          
            stroke(#ffffff);
            noFill();
            rect(223,672,117,50);
          
            fill(#ffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Select",223,672,117,50);
          
        }
        

//Palette 4

        stroke(#ffffff);
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
          
            stroke(#ffffff);
            noFill();
            rect(460,390,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("$250.00",460,390,117,50);
          
        }


        //Buy/Select/Selected Box
        
        
        if (!(owned[3])) {
          
            stroke(#ffffff);
            noFill();
            rect(593,390,117,50);
          
            fill(#ffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",593,390,117,50);
          
        }
 
        if (selected[3]) {
            
            stroke(#00ff00);
            noFill();
            rect(593,390,117,50);
            
            fill(#00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",593,390,117,50);

        }
        
        if (!(selected[3]) && owned[3]) {
          
            stroke(#ffffff);
            noFill();
            rect(593,390,117,50);
          
            fill(#ffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Select",593,390,117,50);
          
        }
        
   
//Palette 5

        stroke(#ffffff);
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
          
            stroke(#ffffff);
            noFill();
            rect(460,530,117,50);
            
            textFont(f,20);
            fill(255);
            textAlign(CENTER,CENTER);
            text ("$250.00",460,530,117,50);
          
        }


        //Buy/Select/Selected Box
        
        
        if (!(owned[4])) {
          
            stroke(#ffffff);
            noFill();
            rect(593,530,117,50);
          
            fill(#ffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",593,530,117,50);
          
        }

        if (selected[4]) {
            
            stroke(#00ff00);
            noFill();
            rect(593,530,117,50);
            
            fill(#00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",593,530,117,50);

        }
        
        if (!(selected[4]) && owned[4]) {
          
            stroke(#ffffff);
            noFill();
            rect(593,530,117,50);
          
            fill(#ffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Select",593,530,117,50);
          
        }
        
 
//Palette 6

        stroke(#ffffff);
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
          
            stroke(#ffffff);
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
          
            stroke(#ffffff);
            noFill();
            rect(593,672,117,50);
          
            fill(#ffffff);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Buy",593,672,117,50);
          
        }
        
        if (selected[5]) {
            
            stroke(#00ff00);
            noFill();
            rect(593,672,117,50);
            
            fill(#00ff00);
            textFont(f,20);
            textAlign(CENTER,CENTER);
            text ("Selected",593,672,117,50);

        }
        
        if (!(selected[5]) && owned[5]) {
          
            stroke(#ffffff);
            noFill();
            rect(593,672,117,50);
          
            fill(#ffffff);
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
        
            fill(#ff0000);
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
            
            fill(#00ff00);
            text ("Selected",255,450,100,50);
            
            stroke (#00ff00);
          
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
            
            fill(#00ff00);
            text ("Selected",255,690,100,50);
            
            stroke (#00ff00);
          
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
            
            fill(#00ff00);
            text ("Selected",570,450,100,50);
            
            stroke (#00ff00);
          
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
            fill(#00ff00);
            text ("Selected",570,690,100,50);
            
            stroke (#00ff00);
          
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
