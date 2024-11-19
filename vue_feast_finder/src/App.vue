<template>
  <div class="full-app-body">
    <div class="nav" v-show="$store.state.token != ''">
      <!-- Do nav next -->
      <div class="nav-links">
        <router-link v-show="getPathName !== 'home'" v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-show="getPathName !== 'groups'" v-bind:to="{ name: 'groups' }">My Feasts</router-link>
        <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
      </div>
  
      <h1 class="logo">Feast Finder</h1>
      <router-link v-if="$store.state.groups.length > 0" v-bind:to="{ name: 'voting', params: { id: '0' } }">View
        Votes</router-link>
    </div>
    <div id="capstone-app" :style="{ backgroundImage: `url(${currentBackground})` }">
      <div :id="idSelector">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // pathName: this.$route.name,
      redShades: [
        "#FF4500", // Orange Red
        "#FF6347", // Tomato
        "#DC143C", // Crimson
        "#FF1493", // Deep Pink (leans red, with a pink tint)
        "#FF5F5F", // Soft Red
        "#B22222"  // Firebrick
      ],
    };
  },

  computed: {
    getPathName() {
      return this.$route.name;
    },
    currentBackground() {
      let pathName = this.$route.name;
      if (pathName === 'home') {
        return "../../homeBackground.jpg";
      } else if (pathName === 'login') {
        return "../../loginBackground.jpg";
      } else if (pathName === 'register') {
        return "../../registerBackground.jpg";
      } else if (pathName === 'groups') {
        return "../../groupsBackground.jpg";
      } else if (pathName === 'voting') {
        return "../../votingBackground.jpg";
      }
      else {
        return "../../homeBackground.jpg";
      }
    },
    getHeaderColor() {
      let pathName = this.$route.name;
      if (pathName === 'home') {
        return this.headerColors[0];
      } else if (pathName === 'login') {
        return this.headerColors[1];
      } else if (pathName === 'register') {
        return this.headerColors[2];
      } else if (pathName === 'groups') {
        return this.headerColors[3];
      } else if (pathName === 'voting') {
        return this.headerColors[4];
      }
      else {
        return this.headerColors[5];
      }
    },
    idSelector() {
      let pathName = this.$route.name;
      if (pathName === 'groups') {
        return 'groups-main';
      } else if (pathName === 'login') {
        return 'login-main';
      } else if (pathName === 'register') {
        return 'register-main';
      } else if (pathName === 'voting') {
        return 'voting-main';
      }
      else {
        return 'home-main';
      }
    }
  },
};
</script>

<style>
html,
body {
  margin: 0;
  padding: 0;
  font-family: "Poppins", sans-serif;
  font-weight: 500;
  font-style: italic;
  height: 100%;
  background-color: #00FFFF;
}

button {
  color: #f2fae6;
  background-color: #E6E6FA;
  /* Increase padding */
  box-shadow: 8px 8px 25px rgba(164, 36, 115, 0.8);
  /* Increase shadow intensity and transparency */
  font-size: 1.2em;
  /* Larger font size */
  font-weight: bold;
  /* Make text bolder */
  border: 2px solid #a42473;
  border-radius: 30px;
  /* Add a contrasting border */
  cursor: pointer;
  transition: all 0.3s ease;
  /* Smooth transition for hover effects */
  cursor: pointer;
}

button:hover {
  background-color: #f88f1f;

  box-shadow: 10px 10px 30px rgba(164, 36, 115, 1);

  transform: scale(1.05);
  /* crazy but cool option */
  /* border: 20px solid;
  
  border-image: url('../borderImage.png') 30 round;

  border-radius: 10px; */

}

.full-app-body {
  display: grid;
  justify-items: center;
  align-items: center;
  height: 100%;
}

#capstone-app {
  padding-top: 10px;
  display: grid;
  justify-items: center;
  color: #E6E6FA;
  background-repeat: repeat;
  background-size: cover;
  max-height: 100vh;
  min-height: 92vh;
  width: 100%;
  padding: 15px;
  box-sizing: border-box;
}

#groups-main {
  display: flex;
  justify-content: center;
  align-items: start;
  gap: 20px;
  max-width: 90vw;
}

#home-main {
  display: flex;
  justify-content: center;
  align-items: start;
  max-width: 90vw;
}

#login-main {
  display: grid;
  grid-template-columns: 1fr;
  align-content: center;
  justify-content: center;
  /* for now solved the scroll issue  */
  max-height: 100vh;
  height: 95vh;
}

#register-main {
  display: grid;
  grid-template-columns: 1fr;
  align-content: center;
  max-height: 100vh;
  height: 95vh;
}

#voting-main {
  display: flex;
  justify-content: center;
  align-items: start;
  gap: 20px;
  max-width: 90vw;
}

.nav {
  display: grid;
  grid-template-columns: 3fr 5fr 3fr;
  background: linear-gradient(50deg,

      #00FFFF,

      #228B22,

      #FF69B4,

      #8A2BE2,

      #7FFF00,

      #f7a029);
  ;
  align-content: center;
  font-size: 1.2em;
  width: 100%;
  height: 8vh;
  justify-items: center;
}

.nav-links {
  display: grid;
  gap: 1vw;
  grid-template-columns: 1fr 1fr 1fr;
  font-size: 1.2em;
  align-items: center;
  justify-items: center;
}

.nav-links a {
  color: #E6E6FA;
  text-decoration: none;
}

/* Set Gradient here */
.logo {
  font-size: 2em;
  color: #E6E6FA;
  font-family: "Shrikhand", serif;
  font-weight: 400;
  font-style: normal;
  margin: 0;
  padding: 0;
}
</style>
