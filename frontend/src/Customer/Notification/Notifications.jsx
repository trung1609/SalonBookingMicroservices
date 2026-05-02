import React, {useEffect} from 'react';
import NotificationCard from "./NotificationCard";
import {useDispatch, useSelector} from "react-redux";
import {fetchNotifications, fetchNotificationsByUser} from "../../Redux/Notifications/action";

const Notifications = () => {
    const dispatch = useDispatch();
    const {auth, notification} = useSelector(store => store);
    useEffect(() => {
        dispatch(fetchNotificationsByUser({
            userId: auth.user?.id,
            jwt: localStorage.getItem("jwt"),
        }));
    }, [dispatch, auth.user?.id]);
    return (
        <div className={'px-5 md:flex flex-col items-center mt-10 min-h-screen'}>
            <div>
                <h1 className={'text-3xl font-bold py-5'}>Notifications</h1>
            </div>

            <div className={'space-y-4 md:w-[35rem]'}>
                {notification.notifications.map((item) =><NotificationCard item={item}/>)}
            </div>
        </div>
    );
};

export default Notifications;