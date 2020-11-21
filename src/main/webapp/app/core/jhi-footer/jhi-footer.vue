<template>
  <div class="rela-block footer">
    <div class="rela-block social-buttons-container">
        <div class="social-button facebook-button"></div>
        <div class="social-button insta-button"></div>
        <div class="social-button twitter-button"></div>
        <div class="social-button pinterest-button"></div>
    </div>
    <div class="rela-block footer-links-container">
        <div class="footer-flex-column">
            <ul> Projects
                <li>Chillwave</li>
                <li>Gluten-free</li>
                <li>Kitsch Cardigan</li>
            </ul>
        </div>
        <div class="footer-flex-column">
            <ul> About us
                <li>Fingerstache</li>
                <li>Lumbersexual</li>
                <li>Lo-Fi Aesthitic</li>
            </ul>
        </div>
        <div class="footer-flex-column">
            <ul> Community
                <li>Kale Chips</li>
                <li>Church Key</li>
                <li>Longboarding</li>
            </ul>
        </div>
        <div class="footer-flex-column">
            <ul> Connect
                <li>Pinterest</li>
                <li>Etsy</li>
                <li>Blog Swag</li>
            </ul>
        </div>
    </div>
  </div>
</template>

<script lang="ts" src="./jhi-footer.component.ts">
</script>

<style scoped>

* {transition: 0.8s ease;} 

.footer {
	min-height: 100px;
	background-color: #282828;
	box-sizing: border-box;
	border-top: 6px solid #e78533;
  color: #fff;
  position:absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  margin-bottom: -27%;
}

.social-buttons-container {
	min-height: 100px;
	width: 100%;
	max-width: 1000px;
	margin: 20px auto 0;
	text-align: center;
}

.social-button {
	display: inline-block;
	position: relative;
	transform: scale(0.75);
	height: 75px;
	width: 75px;
	background-size: cover !important;
	transition: 0.3s ease;
	margin: 14px 2px;
	cursor: pointer;
}

.social-button:hover {
	transform: scale(1);
	transition: 0.3s cubic-bezier(0.3, 0.2, 0, 2.5);
}

.social-button.twitter-button {
	background: url("https://cdn4.iconfinder.com/data/icons/social-icon-4/842/twitter-256.png") no-repeat center;
}

.social-button.facebook-button {
	background: url("https://cdn4.iconfinder.com/data/icons/social-icon-4/842/facebook-256.png") no-repeat center;
}

.social-button.pinterest-button {
	background: url("https://cdn4.iconfinder.com/data/icons/social-icon-4/842/pinterest-256.png") no-repeat center;
}

.social-button.insta-button {
	background: url("https://cdn4.iconfinder.com/data/icons/social-icon-4/842/instagram-256.png") no-repeat center;
}

.footer-links-container {
	display: flex;
	width: 90%;
	max-width: 950px;
	margin: 30px auto 80px;
	text-align: center;
}

.footer-links-container .footer-flex-column {
	position: relative;
	flex: 1 1 0;
	text-align: center;
	border-right: 1px solid #555;
}

.footer-links-container .footer-flex-column:last-child {
	border: none;
}

.footer-links-container .footer-flex-column ul {
	position: relative;
	width: 100%;
	max-width: 200px;
	margin: auto;
	color: #aaa;
	font-size: 18px;
	text-transform: uppercase;
}

.footer-links-container .footer-flex-column li {
	display: block;
	position: relative;
	margin: 15px 7px;
	color: #666;
	text-align: center;
	font-size: 16px;
	text-transform: capitalize;
	cursor: pointer;
}

.footer-links-container .footer-flex-column li:hover {
	color: #999;
}

@media screen and (max-width: 800px) {
	.fixed-nav-bar .logo {
		left: 50%;
		transform: translate(-50%, -50%);
	}

	.deer-section {
		min-height: 660px;
		background-position: right bottom;
		background-size: 300px 425px;
	}
}

@media screen and (max-width: 500px) {
	.social-buttons-container {
		max-width: 200px;
	}
}

</style>
