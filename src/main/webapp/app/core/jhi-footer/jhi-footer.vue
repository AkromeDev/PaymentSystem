<template>
    <div id="footer" class="footer">
        <div id="footer-section">

            <div id="footer-links">
                <div class="footer-group">
                    <h3>Residential</h3>
                    <a href="">Overview</a>
                    <a href="">Maintenance Services</a>
                    <a href="">Installation Services</a>
                    <a href="">Success Stories</a>
                </div>
                <div class="footer-group">
                    <h3>Commercial</h3>
                    <a href="">Overview</a>
                    <a href="">Maintenance Services</a>
                    <a href="">Construction Services</a>
                    <a href="">Projects</a>
                    <a href="">Success Stories</a>
                </div>
                <div class="footer-group">
                    <h3>Company Info</h3>
                    <a href="">Overview</a>
                    <a href="">About Us</a>
                    <a href="">Contact Us</a>
                    <a href="">Our Team</a>
                </div> 
            </div>
            
            <div id="footer-text">
                <div id="footer-contact">
                    <div>
                        <span class="nw footer-contact-company-name">Akroma Coorporation. Solutions for a better world</span>
                        <span class="footer-divider">|</span>
                        <span class="nw">7755 W. Better Rd., Ste. C</span>
                        <span class="footer-divider">|</span>
                        <span class="nw">Yaoundé, CMR 22557</span>
                    </div>
                    <div><span class="footer-divider">|</span></div>
                    <div>
                        <span class="nw">Phone: <a href="tel:554-754-0666">554-754-0666</a></span>
                        <span class="footer-divider">|</span>
                        <span class="nw">Fax: 554-754-0999</span>
                        <span class="footer-divider">|</span>
                        <span class="nw">Contractor #: AKROMA-CORP</span>
                    </div>
                </div>
                
                <div id="footer-copyright">Base content copyright 2020 Akroma & Truth Coorporation. 
                    All rights reserved. Additional content Copyright 2020 Liz Inc. Solutions for a better world. 
                    Duplication in part or in while is strictly prohibited.
                </div>
            </div>

        </div>
    </div>
</template>

<script lang="ts" src="./jhi-footer.component.ts">
</script>

<style scoped>

#footer-section {
  padding:40px 0px 40px 0px;
}
#footer-links {
    width: 100%;
    display: flex;
    justify-content: center;
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
  display: inline-block;
  margin:0px 50px 0px 0px;
}

#footer-text {
    display:block;
    justify-content: center;
    clear:both;
}

#footer-contact {
  color:rgb(10, 6, 6);
  font-size:12px;
  font-weight:300;
  padding:30px 0px 10px 0px;
  text-align: center;
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
  color:#2c2b2b;
  font-size:11px;
  font-weight:300;
  clear:both;
  text-align: center;
}

#footer-divider {
  margin: 4px;
  color: #2c2b2b;
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
}

</style>
