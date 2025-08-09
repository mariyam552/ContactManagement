import React from 'react';
import './header.css';
import { FaUserCircle } from 'react-icons/fa';
import { FiLogOut } from 'react-icons/fi';
import { useNavigate } from 'react-router-dom';
import { FaExpand } from 'react-icons/fa';
import { GiBackwardTime } from "react-icons/gi";

const Header = ({ onLogout, variant }) => {
  const navigate = useNavigate();

  const toggleFullScreen = () => {
    if (!document.fullscreenElement) {
      document.documentElement.requestFullscreen().catch((err) => {
        console.log(`Error attempting to enable full-screen mode: ${err.message}`);
      });
    } else {
      document.exitFullscreen();
    }
  };

  return (
    <div className="main-header">
      <div className="left-section">
        {/* Only Fullscreen Icon */}
        <FaExpand
          className="fullscreen-icon"
          title="Fullscreen"
          onClick={toggleFullScreen}
        />
      </div>

      <div className="center-title">
        {variant === 'profile' ? 'Profile' : 'Contacts'}
      </div>

      <div className="right-section">
        {/* Back Icon (only on Profile page) */}
        {variant === 'profile' && (
          <div
            className="profile-section"
            title="Back"
            onClick={() => navigate('/contacts')}
          >
            <GiBackwardTime className="profile-icon" />
          </div>
        )}
        
     

        {/* Logout Icon */}
        <div
          className="profile-section"
          onClick={onLogout}
          title="Logout"
        >
          <FiLogOut className="profile-icon" />
        </div>

        {/* Profile Icon (only if not on profile page) */}
        {variant !== 'profile' && (
          <div
            className="profile-section"
            onClick={() => navigate('/profile')}
            title="Profile"
          >
            <FaUserCircle className="profile-icon" />
          </div>
        )}
      </div>
    </div>
  );
};

export default Header;
