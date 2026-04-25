import React from 'react'
import SalonCard from './SalonCard'

const SalonList = () => {
  return (
    <div>
        {[1, 1, 1, 1, 1, 1].map((item) => <SalonCard />)}
    </div>
  )
}

export default SalonList
