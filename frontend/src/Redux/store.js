import {applyMiddleware, combineReducers, legacy_createStore} from "redux";
import {thunk} from "redux-thunk";
import salonReducer from "./Salon/reducer";
import bookingReducer from "./Booking/reducer";
import categoryReducer from "./Category/reducer";
import authReducer from "./Auth/reducer";
import reviewReducer from "./Review/reducer";
import notificationReducer from "./Notifications/reducer";
import serviceOfferingReducer from "./Salon Services/reducer";

const rootReducers = combineReducers({
    salon: salonReducer,
    booking: bookingReducer,
    category: categoryReducer,
    auth: authReducer,
    review: reviewReducer,
    notification: notificationReducer,
    service: serviceOfferingReducer
})

export const store = legacy_createStore(rootReducers, applyMiddleware(thunk))