import React, {useState} from 'react'
import SalonDetail from './SalonDetail'
import {Button, Divider} from "@mui/material";
import SalonServiceDetails from "./SalonServiceDetails";

const tabs = [{name: "All Services"}, {name: "Reviews"}, {name: "Create Review"}]
const SalonDetails = () => {
    const [activeTab, setActiveTab] = useState(tabs[0])
    const handleActiveTab = (tab) => () => setActiveTab(tab)
    return (
        <div className='px-5 lg:px-20'>
            <SalonDetail/>
            <div className={'space-y-4'}>
                <div className={'flex gap-2'}>
                    {tabs.map((tab) => <Button
                        onClick={handleActiveTab(tab)}
                        variant={tab.name === activeTab.name ? "contained" : 'outlined'}>
                        {tab.name}</Button>)}
                </div>
                <Divider/>
            </div>
            <div>
                {activeTab.name === "Create Review" ? <div>
                    create review form
                </div> : activeTab.name === "Reviews" ? <div>
                    Review list
                </div> : <div>
                    <SalonServiceDetails/>
                </div>}
            </div>
        </div>
    )
}

export default SalonDetails
