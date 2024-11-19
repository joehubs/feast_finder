<template>
  <div id="login-container">
    <div class="logo-display">

    </div>
    <div id="login">
      <form v-on:submit.prevent="login" id="login-form">
        <h3>Please Sign In</h3>
        <div id="form-info">

          <div role="alert" v-if="invalidCredentials">
            Invalid username and password!
          </div>
          <div role="alert" v-if="this.$route.query.registration">
            Thank you for registering, please sign in.
          </div>
          <div class="form-input-group">
            <label for="username">Email Address</label>
            <input type="text" id="username" v-model="user.username" required autofocus />
          </div>
          <div class="form-input-group">
            <label for="password">Password</label>
            <input type="password" id="password" v-model="user.password" required />
          </div>
          <button type="submit" id="sign-in">Sign in</button>

          <router-link v-bind:to="{ name: 'register' }" :style="{ color: '#f7a94d' }">Need
            an
            account? Sign
            up.</router-link>

        </div>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          console.log(error);
          const response = error.message;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  }
};
</script>

<style scoped>
.logo-display {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  background-image: url('../../src/logo.png');
  background-position: top;
  background-size: cover;
  border-radius: 10px 0 0 10px;
}

#login-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  max-height: 70vh;
  justify-items: center;
  background-image: url('../../borderImage.png');
  border-radius: 20px;
  padding: 30px;
}

#login {
  display: flex;
  flex-direction: column;
  font-weight: bold;
  font-size: larger;
  background: #FF69B4;
  padding: 5%;
  text-align: center;
  border-radius: 0 10px 10px 0;
}

#login-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-items: center;
  width: 100%;
  gap: 5px;
}

#login-form h3 {
  color: #00FFFF;
  background-color: #f7a029;
  padding: 2vh;
  border-radius: 10px;
}

#form-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-items: center;
  width: 90%;
  background-color: #00FFFF;
  color: #8A2BE2;
  border-radius: 10px;
  padding: 10px 0;
}

#sign-in {
  background-color: #8A2BE2;
  color: #E6E6FA;
  font-size: larger;
  border-radius: 10px;
  font-family: "Poppins", sans-serif;
  padding: 10px;
  margin: 20px;
}

.form-input-group {
  margin-bottom: 1rem;
  display: flex;
  flex-direction: column;
}
</style>