<template>
    <div id="footer" class="footer">
        <div id="footer-section">
  <div id="footer-copy" class="content-safe">
    
    <div class="footer-group footer-group-logo">
      <div id="footer-logo"></div>
      <div id="footer-join-our-team" onclick="javascript:alert('Coming soon');"></div>
      <div id="footer-facebook" class="footer-social-media-link" onclick="window.open('https://www.facebook.com/pages/Bruce-Heating-Air-Conditioning/153829408062627', '_blank');"></div>
      <div id="footer-twitter" class="footer-social-media-link" onclick="window.open('https://twitter.com/BruceIncHVAC', '_blank');"></div>
      <div id="footer-blogspot" class="footer-social-media-link" onclick="window.open('https://richlandpascokennewickhvac.blogspot.com', '_blank');"></div>
    </div>
    <div class="footer-group">
      <h3>Residential</h3>
      <a href="https://s.codepen.io/brianrak/debug/wBopQJ">Overview</a>
      <a href="https://s.codepen.io/brianrak/debug/wBopQJ#maintenance">Maintenance Services</a>
      <a href="https://s.codepen.io/brianrak/debug/wBopQJ#installation">Installation Services</a>
      <a href="https://s.codepen.io/brianrak/debug/yNJyLm">Success Stories</a>
    </div>
    <div class="footer-group">
      <h3>Commercial</h3>
      <a href="https://s.codepen.io/brianrak/debug/GgWYgR">Overview</a>
      <a href="https://s.codepen.io/brianrak/debug/GgWYgR#maintenance">Maintenance Services</a>
      <a href="https://s.codepen.io/brianrak/debug/GgWYgR#construction">Construction Services</a>
      <a href="https://s.codepen.io/brianrak/debug/gbmBLa">Projects</a>
      <a href="https://s.codepen.io/brianrak/debug/bNWbZo">Success Stories</a>
    </div>
    <div class="footer-group">
      <h3>Company Info</h3>
      <a href="https://s.codepen.io/brianrak/debug/vOKENp">Overview</a>
      <a href="https://s.codepen.io/brianrak/debug/vOKENp#about">About Us</a>
      <a href="https://s.codepen.io/brianrak/debug/vOKENp#contact">Contact Us</a>
      <a href="https://s.codepen.io/brianrak/debug/vOKENp#team">Our Team</a>
    </div> 
    <div class="clear-floats"></div>
    
    <div id="footer-contact">
      <div>
        <span class="nw footer-contact-company-name">Bruce Inc. Heating and Air Conditioning</span>
        <span class="footer-divider">|</span>
        <span class="nw">5115 W. Brinkley Rd., Ste. C</span>
        <span class="footer-divider">|</span>
        <span class="nw">Kennewick, WA 99338</span>
      </div>
      <div><span class="footer-divider">|</span></div>
      <div>
        <span class="nw">Phone: <a href="tel:509-734-0669">509-734-0669</a></span>
        <span class="footer-divider">|</span>
        <span class="nw">Fax: 509-783-7090</span>
        <span class="footer-divider">|</span>
        <span class="nw">Contractor #: BRUCEMI951LB</span>
      </div>
    </div>
    
    <div id="footer-copyright">Base content copyright 2014 Lennox Industries. All rights reserved. Additional content Copyright 2014 Bruce Inc. Heating and Air Conditioning. Duplication in part or in while is strictly prohibited.</div>
  </div>
</div>

<!-- Preloads, super old-school -->
<div style="display:none;">
  <img src="http://www.braksoftware.com/codepen/bruce/v1/join-our-team-button-over-large.png" />
  <img src="http://www.braksoftware.com/codepen/bruce/v1/join-our-team-button-over-medium.png" />
</div>
    </div>
</template>

<script lang="ts" src="./jhi-footer.component.ts">
</script>

<style scoped>
    #footer-section {
  padding:40px 0px 40px 0px;
}

#footer-copy {
  
}

#footer-section h3 {
  color:#801b1b;
  font-size:16px;
  font-weight:400;
  padding:0;
  margin:0px 0px 15px 0px;
}

#footer-section a {
  color:#5c1818;
  font-size:14px;
  line-height:22px;
  font-weight:300;
  text-decoration:none;
  display:block;
}

#footer-section a:hover {
  text-decoration:underline;
}

.footer-group {
  float:left;
  margin:0px 50px 0px 0px;
}

#footer-logo {
  background-image:url(http://www.braksoftware.com/codepen/bruce/v1/bruce-logo-footer-large.png);
  background-size:100% 100%;
  background-repeat:no-repeat;
  width:199px;
  height:46px;
  margin:0px 60px 50px 0px;
}

#footer-join-our-team {
  cursor:pointer;
  background-image:url(http://www.braksoftware.com/codepen/bruce/v1/join-our-team-button-large.png);
  background-size:100% 100%;
  background-repeat:no-repeat;
  width:184px;
  height:41px;
  margin:0px 0px 10px 0px;
}

#footer-join-our-team:hover {
  background-image:url(http://www.braksoftware.com/codepen/bruce/v1/join-our-team-button-over-large.png);
}

.footer-social-media-link {
  display:inline-block;
  cursor:pointer;
  background-position:center;
  background-repeat:no-repeat;
  width:27px;
  height:26px;
  margin:4px;
}

#footer-facebook {
  background-image:url(http://www.braksoftware.com/codepen/bruce/v1/facebook-white-large.png); 
}

#footer-twitter {
  background-image:url(http://www.braksoftware.com/codepen/bruce/v1/twitter-white-large.png);
}

#footer-blogspot {
  background-image:url(http://www.braksoftware.com/codepen/bruce/v1/blogger-white-large.png);
}

#footer-contact {
  color:white;
  font-size:12px;
  font-weight:300;
  padding:30px 0px 10px 0px;
}

#footer-contact div {
  display:inline;
}

#footer-contact a {
  display:inline;
  color:inherit;
  font-size:inherit;
  line-height:inherit;
}

#footer-copyright {
  color:#eeeeee;
  font-size:11px;
  font-weight:300;
  clear:both;
  align-content:center;
}

.footer-divider {
  margin:0px 4px 0px 4px;
}

@media screen and (min-width:600px) and (max-width: 999px) {
  #footer-section h3 {
    font-size:16px;
    font-weight:400;
    margin:0px 0px 15px 0px;
  }

  #footer-section a {
    font-size:13px;
    font-weight:300;
    line-height:22px;
  }
  
  .footer-group {
    margin:0px 20px 0px 0px;
  }
  
  #footer-logo {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/bruce-logo-footer-medium.png);
    width:121px;
    height:28px;
    margin:4px 0px 37px 0px;
  }
  
  #footer-join-our-team {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/join-our-team-button-medium.png);
    width:120px;
    height:27px;
    margin:0px 0px 10px 0px;
  }
  
  #footer-join-our-team:hover {
  background-image:url(http://www.braksoftware.com/codepen/bruce/v1/join-our-team-button-over-medium.png);
}
  
  .footer-social-media-link {
    width:16px;
    height:16px;
    margin:4px;
  }
  
  #footer-facebook {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/facebook-white-medium.png); 
  }

  #footer-twitter {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/twitter-white-medium.png);
  }

  #footer-blogspot {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/blogger-white-medium.png);
  }
  
  #footer-contact {
    font-size:13px;
    font-weight:300;
  }
  
  #footer-contact div {
    /* So that the address and phone numbers are on separate lines */
    display:block;
  }
  

  #footer-contact a {
    display:inline;
    color:inherit;
    font-size:inherit;
    line-height:inherit;
  }
  
  #footer-contact div:nth-last-child(2) {
    /* So that there is no divider between the address and phone number lines */
    display:none;
  }
}

@media screen and (max-width: 599px) {
  #footer-section {
    padding:25px 0px 25px 0px;
  }
  
  #footer-section h3 {
    font-size:20px;
    font-weight:400;
    margin:4px 0px 10px 0px;
  }

  #footer-section a {
    font-size:17px;
    font-weight:300;
    line-height:28px;
  }
  
  .footer-group {
    width:300px;
    margin:0px 20px 20px 0px;
  }
  
  .footer-group > * {
    vertical-align:top;
  }
  
  .footer-group-logo {
    margin:0px 0px 20px 0px
  }
  
  
  #footer-logo {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/bruce-logo-footer-medium.png);
    width:120px;
    height:27px;
    margin:0px 10px 20px 0px;
  }
  
  #footer-join-our-team {
    display:inline-block;
    margin:0px 10px 10px 0px;
  }
  
  .footer-social-media-link {
    margin:8px 4px 0px 0px;
  }
  
  #footer-contact {
    font-size:14px;
    line-height:24px;
    padding:10px 0px 10px 0px;
  }
  
  #footer-contact span.nw {
    display:block;
  }
  
  .footer-contact-company-name {
    font-weight:400;
  }
  
  .footer-divider {
    display:none;
  }

  #footer-contact a {
    display:inline;
    color:inherit;
    font-size:inherit;
    line-height:inherit;
  }
}

/* Retina graphics here */
@media screen and (-webkit-min-device-pixel-ratio: 2) and (min-width:1000px) { 
  #footer-logo {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/bruce-logo-footer-large@2x.png);
  }
  
  #footer-join-our-team {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/join-our-team-button-large@2x.png);
  }
}

@media screen and (-webkit-min-device-pixel-ratio: 2) and (min-width:600px) and (max-width: 999px) {
  #footer-logo {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/bruce-logo-footer-medium@2x.png);
  }
  
  #footer-join-our-team {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/join-our-team-button-medium@2x.png);
  }
}

@media screen and (-webkit-min-device-pixel-ratio: 2) and (max-width: 599px) {
  #footer-logo {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/bruce-logo-footer-medium@2x.png);
  }
  
  #footer-join-our-team {
    background-image:url(http://www.braksoftware.com/codepen/bruce/v1/join-our-team-button-large@2x.png);
  }
}

</style>
