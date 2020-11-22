<template>
    <b-navbar toggleable="md" type="dark" class="jh-navbar">
        <b-navbar-brand class="logo" b-link to="/">
            <span class="logo-img"></span>
            <span class="navbar-title">PayMyBuddy</span> <span class="navbar-version">{{version}}</span>
        </b-navbar-brand>      
        <b-navbar-toggle 
        right 
        class="jh-navbar-toggler d-lg-none" 
        href="javascript:void(0);"  
        data-toggle="collapse" 
        target="header-tabs" 
        aria-expanded="false" 
        aria-label="Toggle navigation">
            <font-awesome-icon icon="bars" />
        </b-navbar-toggle>
           
        <b-collapse is-nav id="header-tabs">
            <b-navbar-nav class="ml-auto">
                <b-nav-item to="/" exact>
                    <span>
                        <font-awesome-icon icon="home" />
                        <span>Home</span>
                    </span>
                </b-nav-item>
                <b-nav-item-dropdown
                    right
                    id="entity-menu"
                    v-if="authenticated"
                    active-class="active" class="pointer">
                    <span slot="button-content" class="navbar-dropdown-menu">
                        <font-awesome-icon icon="th-list" />
                        <span>Entities</span>
                    </span>
                    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
                </b-nav-item-dropdown>
                <b-nav-item-dropdown
                    right
                    id="admin-menu"
                    v-if="hasAnyAuthority('ROLE_ADMIN') && authenticated"
                    :class="{'router-link-active': subIsActive('/admin')}"
                    active-class="active"
                    class="pointer">
                    <span slot="button-content" class="navbar-dropdown-menu">
                        <font-awesome-icon icon="cogs" />
                        <span>Administration</span>
                    </span>
                    <b-dropdown-item to="/admin/user-management" active-class="active">
                        <font-awesome-icon icon="user" />
                        <span>User management</span>
                    </b-dropdown-item>
                    <b-dropdown-item  to="/admin/jhi-metrics" active-class="active">
                        <font-awesome-icon icon="tachometer-alt" />
                        <span>Metrics</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/admin/jhi-health" active-class="active">
                        <font-awesome-icon icon="heart" />
                        <span>Health</span>
                    </b-dropdown-item>
                    <b-dropdown-item  to="/admin/jhi-configuration" active-class="active">
                        <font-awesome-icon icon="list" />
                        <span>Configuration</span>
                    </b-dropdown-item>
                    <b-dropdown-item  to="/admin/audits" active-class="active">
                        <font-awesome-icon icon="bell" />
                        <span>Audits</span>
                    </b-dropdown-item>
                    <b-dropdown-item  to="/admin/logs" active-class="active">
                        <font-awesome-icon icon="tasks" />
                        <span>Logs</span>
                    </b-dropdown-item>
                    <b-dropdown-item v-if="swaggerEnabled"  to="/admin/docs" active-class="active">
                        <font-awesome-icon icon="book" />
                        <span>API</span>
                    </b-dropdown-item>
                    <b-dropdown-item v-if="!inProduction"  href='./h2-console' target="_tab">
                        <font-awesome-icon icon="hdd" />
                        <span>Database</span>
                    </b-dropdown-item>
                </b-nav-item-dropdown>
                <b-nav-item-dropdown
                    right
                    href="javascript:void(0);"
                    id="account-menu"
                    :class="{'router-link-active': subIsActive('/account')}"
                    active-class="active"
                    class="pointer">
                    <span slot="button-content" class="navbar-dropdown-menu">
                        <font-awesome-icon icon="user" />
                        <span>
                            Account
                        </span>
                    </span>
                    <b-dropdown-item to="/account/settings" tag="b-dropdown-item" v-if="authenticated" active-class="active">
                        <font-awesome-icon icon="wrench" />
                        <span>Settings</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/account/password" tag="b-dropdown-item" v-if="authenticated" active-class="active">
                        <font-awesome-icon icon="lock" />
                        <span>Password</span>
                    </b-dropdown-item>
                    <b-dropdown-item v-if="authenticated"  v-on:click="logout()" id="logout" active-class="active">
                        <font-awesome-icon icon="sign-out-alt" />
                        <span>Sign out</span>
                    </b-dropdown-item>
                    <b-dropdown-item v-if="!authenticated"  v-on:click="openLogin()" id="login" active-class="active">
                        <font-awesome-icon icon="sign-in-alt" />
                        <span>Sign in</span>
                    </b-dropdown-item>
                    <b-dropdown-item to="/register" tag="b-dropdown-item" id="register" v-if="!authenticated" active-class="active">
                        <font-awesome-icon icon="user-plus" />
                        <span>Register</span>
                    </b-dropdown-item>
                </b-nav-item-dropdown>
            </b-navbar-nav>
        </b-collapse>
    </b-navbar>
</template>

<script lang="ts" src="./jhi-navbar.component.ts">
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* ==========================================================================
    Navbar
    ========================================================================== */
.navbar-version {
  font-size: 10px;
  color: #ccc;
}

.jh-navbar {
  background-color: #353d47;
  padding: 0.2em 1em;
}

.jh-navbar .profile-image {
  margin: -10px 0px;
  height: 40px;
  width: 40px;
  border-radius: 50%;
}

.jh-navbar .dropdown-item.active,
.jh-navbar .dropdown-item.active:focus,
.jh-navbar .dropdown-item.active:hover {
  background-color: #353d47;
}

.jh-navbar .dropdown-toggle::after {
  margin-left: 0.15em;
}

.jh-navbar ul.navbar-nav {
  padding: 0.5em;
}

.jh-navbar .navbar-nav .nav-item {
  margin-left: 1.5rem;
}

.jh-navbar a.nav-link {
  font-weight: 400;
}

.jh-navbar .jh-navbar-toggler {
  color: #ccc;
  font-size: 1.5em;
  padding: 10px;
}

.jh-navbar .jh-navbar-toggler:hover {
  color: #fff;
}

@media screen and (min-width: 768px) {
  .jh-navbar-toggler {
    display: none;
  }
}

@media screen and (min-width: 768px) and (max-width: 1150px) {
  span span{
    display:none;
  }
}

.navbar-title {
  display: inline-block;
  vertical-align: middle;
  color: white;
}

/* ==========================================================================
    Logo styles
    ========================================================================== */
.navbar-brand.logo {
  padding: 5px 15px;
}

.logo .logo-img {
  height: 45px;
  display: inline-block;
  vertical-align: middle;
  width: 70px;
}

.logo-img {
  height: 100%;
  background: url("../../../content/images/logo-jhipster.png") no-repeat center
    center;
  background-size: contain;
  width: 100%;
  filter: drop-shadow(0 0 0.05rem white);
}

/* ==========================================================================
    New Look atempt
    ========================================================================== */

.fixed-nav-bar {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	min-height: 100px;
	padding: 0 25px;
	box-sizing: border-box;
	background-color: rgba(255,255,255,0.02);
	box-shadow: 0 0 15px 2px rgba(0,0,0,0.5);
	z-index: 100;
	backface-visibility: hidden;
	transition: 0.35s ease;
}

.fixed-nav-bar .logo {
	position: absolute;
	left: 40px;
	top: 50%;
	transform: translateY(-50%);
	text-transform: uppercase;
	color: #ccc;
	font-size: 28px;
	font-weight: 300;
	cursor: pointer;
}

.fixed-nav-bar .logo span {
	color: #e78533;
	font-weight: 600;
}

.fixed-nav-bar.scrolled {
	min-height: 60px;
	background-color: #fdfdfd;
	box-shadow: 0 0 30px 3px rgba(0,0,0,0.6);
}

.fixed-nav-bar.scrolled .logo {
	color: #000;
}

.fixed-nav-bar.scrolled .menu-button-label .white-bar {
	background-color: #000;
}

.drop-down-container {
	height: 15%;
	width: 90%;
	left: 160%;
	margin-top: 5px;
	transform: translateX(-50%);
	transition: 0.3s ease;
}

.drop-down-item {
	position: absolute;
	top: 0;
	left: 0;
	right: 8px;
	bottom: 0;
	background-color: #222;
}

.drop-down-item:before {
	content: "";
	position: absolute;
	right: -8px;
	height: 100%;
	width: 8px;
	transition: 0.25s ease;
	background: linear-gradient(to left, #e78533 50%, #222 50%);
	background-size: 200% 100%;
	background-position: left;
}

.drop-down-item:hover::before {
	background-position: right;
}

.the-bass {
	position: fixed;
	height: 0px;
	width: 360px;
	right: 0;
	top: 100px;
	background-color: rgba(0,0,0,0.7);
	transition: 0.35s ease, height 0.35s 0.3s ease;
	z-index: 90;
}

.the-bass.scrolled {
	top: 60px;
}

.the-bass.dropped {
	height: 60vh;
	padding: 5px 0;
	transition: 0.35s ease;
}

.the-bass.dropped .drop-down-container {
	left: 50%;
}

.the-bass.dropped .drop-down-container:nth-child(1) {
	transition: 0.3s ease, left 0.4s 0.4s ease;
}

.the-bass.dropped .drop-down-container:nth-child(2) {
	transition: 0.3s ease, left 0.4s 0.46s ease;
}

.the-bass.dropped .drop-down-container:nth-child(3) {
	transition: 0.3s ease, left 0.4s 0.52s ease;
}

.the-bass.dropped .drop-down-container:nth-child(4) {
	transition: 0.3s ease, left 0.4s 0.58s ease;
}

.the-bass.dropped .drop-down-container:nth-child(5) {
	transition: 0.3s ease, left 0.4s 0.64s ease;
}

.the-bass.dropped .drop-down-container:nth-child(6) {
	transition: 0.3s ease, left 0.4s 0.7s ease;
}

.menu-button-label {
	position: absolute;
	height: 48px;
	width: 48px;
	top: 50%;
	right: 25px;
	transform: translateY(-50%);
	transition: 0.4s ease;
}

.menu-button-label .white-bar {
	position: absolute;
	height: 2px;
	width: 66%;
	background-color: #ccc;
	left: 50%;
	transform: translateX(-50%);
	transition: 0.4s ease, width 0.3s ease;
}

.menu-button-label .white-bar:nth-child(1) {
	top: 24%;
}

.menu-button-label .white-bar:nth-child(2),
.menu-button-label .white-bar:nth-child(3) {
	top: 47%;
}

.menu-button-label .white-bar:nth-child(4) {
	top: 71%;
}

#menuButton {
	display: none;
}

#menuButton:checked+ .menu-button-label .white-bar:nth-child(1),
#menuButton:checked+ .menu-button-label .white-bar:nth-child(4) {
	width: 0%;
}

#menuButton:checked+ .menu-button-label .white-bar:nth-child(2) {
	transform: translateX(-50%) rotate(45deg);
}

#menuButton:checked+ .menu-button-label .white-bar:nth-child(3) {
	transform: translateX(-50%) rotate(-45deg);
}

.rela-block {
	display: block;
	position: relative;
	overflow: hidden;
}

.cover-before::before {
	content: "";
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
}

</style>
