import axios from 'axios';
//Andy said in the gotcha's and on the captsone review final not to set this for some reason
// const http = axios.create({
//     baseURL: import.meta.env.VITE_REMOTE_API
// });

export default {
    getRestaurants(data) {
        return axios.get(`/restaurants/${data}`);
    },
    // getRestaurant(id) {
    //     return http.get(`/restaurants/${id}`)
    // },
    // createRestaurant(restaurant) {
    //     return http.post('/restaurants', restaurant)
    // },
    // updateRestaurant(id, restaurant) {
    //     return http.put(`/restaurants/${id}`, restaurant)
    // },
    // deleteRestaurant(id) {
    //     return http.delete(`/restaurants/${id}`)
    // }
}