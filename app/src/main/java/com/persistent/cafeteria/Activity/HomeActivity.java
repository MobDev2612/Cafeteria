package com.persistent.cafeteria.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.persistent.cafeteria.Fragment.CartFragment;
import com.persistent.cafeteria.Fragment.FeedbackFragment;
import com.persistent.cafeteria.Fragment.HomeFragment;
import com.persistent.cafeteria.Fragment.OrderProgressFragment;
import com.persistent.cafeteria.Fragment.WalletFragment;
import com.persistent.cafeteria.R;
import com.persistent.cafeteria.Utils.Config;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.setCheckedItem(0);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_home));
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
//        changeFragment(new HomeFragment());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        showDrawer();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        MenuItem item = menu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(item, R.layout.cart_icon);
        RelativeLayout count = (RelativeLayout) MenuItemCompat.getActionView(item);
        TextView notifCount = (TextView) count.findViewById(R.id.actionbar_notifcation_textview);
        if (Config.cartItems.size() > 0) {
            notifCount.setVisibility(View.VISIBLE);
            notifCount.setText(String.valueOf(Config.cartItems.size()));
        } else {
            notifCount.setVisibility(View.GONE);
        }
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Config.cartItems.isEmpty()) {
                    Toast.makeText(HomeActivity.this, "Cart is empty. Add Products to cart", Toast.LENGTH_LONG).show();
                } else {
                    changeFragment(new CartFragment());
                }
            }
        });


        MenuItem orderItem = menu.findItem(R.id.action_orders);
        MenuItemCompat.setActionView(orderItem, R.layout.order_icon);
        RelativeLayout countOrder = (RelativeLayout) MenuItemCompat.getActionView(orderItem);
        TextView notifOrderCount = (TextView) countOrder.findViewById(R.id.actionbar_notifcation_textview);
        if (Config.orderPlaced) {
            notifOrderCount.setVisibility(View.VISIBLE);
            notifOrderCount.setText(String.valueOf("1"));
        } else {
            notifOrderCount.setVisibility(View.GONE);
        }
        countOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Config.orderPlaced) {
                    Toast.makeText(HomeActivity.this, "No Orders to show", Toast.LENGTH_SHORT).show();
                } else {
                    changeFragment(new OrderProgressFragment());
                }
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            if (Config.cartItems.isEmpty()) {
                Toast.makeText(this, "Cart is empty. Add Products to cart", Toast.LENGTH_LONG).show();
            } else {
               changeFragment(new CartFragment());
            }
            return true;
        }

        if (id == R.id.action_orders) {
            if (!Config.orderPlaced) {
                Toast.makeText(this, "No Orders to show", Toast.LENGTH_LONG).show();
            } else {
                changeFragment(new OrderProgressFragment());
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id== R.id.nav_home){
            changeFragment(new HomeFragment());
        } else if (id == R.id.nav_my_orders) {

        } else if (id == R.id.nav_my_events) {

        } else if (id == R.id.nav_my_menu) {

        } else if (id == R.id.nav_view_offers) {

        } else if (id == R.id.nav_my_favorites) {

        } else if (id == R.id.nav_my_wallet) {
            Config.fromPlaceOrder = false;
            changeFragment(new WalletFragment());
        } else if (id == R.id.nav_my_feedback) {
            changeFragment(new FeedbackFragment());
        } else if (id == R.id.nav_my_invoice) {

        } else if (id == R.id.nav_contact_us) {

        } else if (id == R.id.nav_sign_out) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment fragment) {
//        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateCount();
        }
    };

    public void updateCount() {
        invalidateOptionsMenu();
    }
//    /**
//     * Shows navigation drawer
//     */
//    public void showDrawer() {
//        toggle.setDrawerIndicatorEnabled(true);
//    }
//
//    /**
//     * Shows navigation drawer arrow
//     */
//    public void showArrow() {
//        toggle.setDrawerIndicatorEnabled(false);
//    }
}
