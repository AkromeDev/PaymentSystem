<template>
    <div class="home row">
        <div class="col-md-3">
            <span class="hipster img-fluid rounded"></span>
        </div>
            <div class="col-md-9">

            <h1 class="display-4">Welcome to PayMyBuddy!</h1>
            <p class="lead">Send & recieve money to your contacts</p>

            <p>
                The app that will falicitate your transactions with all your friends.
                Our goal is to simplify money transaction, with our app you will be able to:
            </p>

            <ul>
                <li>Add your friends from their email or pseudo</li>
                <li>Send & recieve  money to your contacts</li>
                <li>Quickly check the overview of your finances </li>
            </ul>

            <div>
                <div class="alert alert-success" v-if="authenticated">
                    <span v-if="username">You are logged in as user "{{username}}"</span>
                </div>
            </div>

        <main class="hero-foot center">
            <div id="grid" style="width: 768px;">
                <div id="b" class="center-column">
                    <p class="title has-text-white">
                        <i class="fas fa-tachometer-alt has-text-grey-light"
                            style="width: 0.75em; height: 0.75em;"></i>
                        2<span class="is-size-5">s/transaction</span>
                    </p>
                    <p class="subtitle has-text-white is-7">The fastest there is</p>
                </div>
                <div id="c" class="center-column" style="border-left: 1px solid gray;">
                    <p class="title has-text-white">1.500.000</p>
                    <p class="subtitle is-7 has-text-white">Users Worldwide</p>
                </div>
                <div id="d" class="center-column" style="border-left: 1px solid gray;">
                    <p class="title has-text-white">0</p>
                    <p class="subtitle is-7 has-text-white">Complications</p>
                </div>
                <div id="e" class="center-column">
                    <a class="button is-danger is-inverted is-rounded is-outlined has-text-weight-bold"
                        style="width: 100%; border: 0.15em solid white;" v-on:click="openLogin()">
                        Sign in
                    </a>
                    <a id="register-button" class="button is-danger is-inverted is-rounded is-outlined has-text-weight-bold"
                        style="width: 100%; border: 0.15em solid white;" href="/register">
                        register
                    </a>
                </div>
            </div>
        </main>

            <p>
                <span>If you like PayMyBuddy, don't forget to follow us</span> <a id="linkedin-link" href="https://www.linkedin.com/in/jo%C3%A9-c-7a9727b9/" target="blank">Everywhere</a>!
            </p>
        </div>
    </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>

<style scoped>

    .home {
        color: white;
    }
    #a { grid-area: a; }
    #b { grid-area: b; margin-top: auto;}
    #c { grid-area: c; margin-top: auto;}
    #d { grid-area: d; margin-top: auto;}
    #e { grid-area: e; text-decoration: none;}

    #grid {
        width: 768px;
        padding: 2rem;
        display: grid;
        text-align: center;
        vertical-align: middle;
        grid-template-areas: 
            "b c d e";

        grid-row-gap: 1rem;
    }

    @media (max-width: 512px) {
        #grid {
            grid-template-areas: 
                "b c d"
                "e e e";
        }
    }

    main {
        padding: 1,5rem;
    }

    .button {
        margin-top: 3px
    }

    #register-button {
        text-decoration: none;
    }

    #linkedin-link {
        color: orange;
    }
</style>